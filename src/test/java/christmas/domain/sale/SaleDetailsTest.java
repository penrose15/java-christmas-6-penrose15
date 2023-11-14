package christmas.domain.sale;

import christmas.domain.calendar.EventCalendar;
import christmas.domain.order.Orders;
import christmas.domain.sale.enums.BenefitCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SaleDetailsTest {

    Orders orders;
    EventCalendar eventCalendar;
    ChristmasDDaySale christmasDDaySale;
    SaleDetails saleDetails;
    @BeforeEach
    void setUp() {
        orders = new Orders();
        eventCalendar = new EventCalendar();
        christmasDDaySale = new ChristmasDDaySale();

        saleDetails = new SaleDetails(orders, eventCalendar, christmasDDaySale);
    }

    @Test
    void 총주문_금액_계산() {
        String inputDate = "3";
        String inputOrders = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        eventCalendar.takeReservation(inputDate);
        orders.takeOrders(inputOrders);

        int resultTotalPrice = saleDetails.calculateTotalPrice();

        assertThat(resultTotalPrice)
                .isEqualTo(142_000);
    }

    @Test
    void 할인_금액_계산() {
        String inputDate = "25";
        String inputOrders = "양송이수프-2,티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        eventCalendar.takeReservation(inputDate);
        orders.takeOrders(inputOrders);

        int resultTotalPrice = saleDetails.calculateTotalSaleAmount();

        assertThat(resultTotalPrice)
                .isEqualTo(8_446);
    }

    @Test
    void 총주문_금액이_10000미만인_경우_할인금액_0_테스트() {
        String inputDate = "25";
        String inputOrders = "타파스-1,제로콜라-1";

        eventCalendar.takeReservation(inputDate);
        orders.takeOrders(inputOrders);

        int resultTotalPrice = saleDetails.calculateTotalSaleAmount();

        assertThat(resultTotalPrice)
                .isEqualTo(0);
    }

}