package christmas.domain.sale;

import christmas.domain.calendar.EventCalendar;
import christmas.domain.order.Orders;
import christmas.domain.sale.enums.Giveaway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BenefitDetailsTest {

    Orders orders;
    EventCalendar eventCalendar;
    ChristmasDDaySale christmasDDaySale;

    SaleDetails saleDetails;

    BenefitDetails benefitDetails;

    @BeforeEach
    void init() {
        orders = new Orders();
        eventCalendar = new EventCalendar();
        christmasDDaySale = new ChristmasDDaySale();

        saleDetails = new SaleDetails(orders, eventCalendar, christmasDDaySale);
        benefitDetails = new BenefitDetails(saleDetails);
    }

    void setUp(String date, String inputOrders) {
        eventCalendar.takeReservation(date);
        orders.takeOrders(inputOrders);
    }

    @Test
    void 할인전_금액이_잘_계산되는지_확인() {
        setUp("3", "양송이수프-1,티본스테이크-1,초코케이크-1,제로콜라-1");

        benefitDetails.calculateTotalPrice();
        int result = benefitDetails.getTotalPrice();

        assertThat(result)
                .isEqualTo(79000);
    }

    /*
     * 10일 기준 할인 예상액
     * 크리스마스디데이할인 1900
     * 평일할인 2023
     * 특별할인 yes 1000
     *
     * 예상 할인 금액 : 4923
     * */

    @Test
    void 할인_금액이_잘_계산되는지_확인() {
        setUp("10", "양송이수프-1,티본스테이크-1,초코케이크-1,제로콜라-1");

        benefitDetails.calculateTotalPrice();
        benefitDetails.calculateTotalSaleAmount();
        int result = benefitDetails.getTotalSaleAmount();



        assertThat(result)
                .isEqualTo(4923);
    }

    @Test
    void 총주문금액이_120_000원이_넘으면_증정품을_주는지_확인() {
        setUp("1", "해산물파스타-2,크리스마스파스타-2,아이스크림-1");

        benefitDetails.calculateTotalPrice();
        Giveaway giveaway = benefitDetails.calculateFreeGiftPrice();

        int result = giveaway.getProductPrice();


        assertThat(result)
                .isEqualTo(25000);
    }

    @Test
    void 총주문금액이_120_000원이_넘지_않으면_증정품을_주지_않는지_확인() {
        setUp("1", "티본스테이크-2");

        benefitDetails.calculateTotalPrice();
        Giveaway giveaway = benefitDetails.calculateFreeGiftPrice();

        int result = giveaway.getProductPrice();


        assertThat(result)
                .isEqualTo(0);
    }

    /*
     * 할인 전 금액 - 149000원
     *
     * 10일 기준 할인 예상액
     * 크리스마스디데이할인 1900
     * 평일할인 2023 * 2 = 4046
     * 특별할인 yes 1000
     * 증정품 샴페인 25000
     * 예상 혜택 금액 : 31946
     * */

    @Test
    void 총_혜택금액이_잘_나오는지_확인() {
        setUp("10", "양송이수프-1,티본스테이크-2,초코케이크-2,제로콜라-1");

        benefitDetails.calculateTotalPrice();
        benefitDetails.calculateTotalSaleAmount();
        int result = benefitDetails.calculateTotalBenefitAmount();

        assertThat(result)
                .isEqualTo(31946);
    }

    @Test
    void 최종_할인금액_이_잘_나오는지_확인() {
        setUp("10", "양송이수프-1,티본스테이크-2,초코케이크-2,제로콜라-1");

        benefitDetails.calculateTotalPrice();
        benefitDetails.calculateTotalSaleAmount();
        int result = benefitDetails.calculateFinalPrice();

        assertThat(result)
                .isEqualTo(142054);
    }

    @Test
    void 총_혜택_메시지가_잘나오는지_확인() {
        setUp("10", "양송이수프-1,티본스테이크-2,초코케이크-2,제로콜라-1");

        benefitDetails.calculateTotalPrice();
        String result = benefitDetails.getTotalBenefitMessage();

        System.out.println(result);

        assertThat(result)
                .contains("크리스마스 디데이 할인: -1,900원",
                        "평일 할인: -4,046",
                        "특별 할인: -1,000원",
                        "증정 이벤트: -25,000원");
    }

}