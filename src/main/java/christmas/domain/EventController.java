package christmas.domain;

import christmas.global.view.io.EventCalendarView;
import christmas.global.view.io.OrdersView;
import christmas.global.view.io.BenefitDetailsView;
import christmas.global.view.output.OutputView;

import static christmas.global.view.message.OrderDynamicMessage.SHOW_EVENT;

public class EventController {
    private final EventCalendarView eventCalendarView;
    private final OrdersView ordersView;
    private final BenefitDetailsView benefitDetailsView;

    public EventController(EventCalendarView eventCalendarView, OrdersView ordersView, BenefitDetailsView benefitDetailsView) {
        this.eventCalendarView = eventCalendarView;
        this.ordersView = ordersView;
        this.benefitDetailsView = benefitDetailsView;
    }

    public void play() {
        takeReservation();
        showOrderMenus();
        showEventPreview();
    }
    public void takeReservation() {
        int date = inputVisitDate();

        inputOrders();
        OutputView.println(SHOW_EVENT.get(date));
    }

    public int inputVisitDate() {
        return eventCalendarView.inputVisitDate();
    }

    public void inputOrders() {
        ordersView.inputOrders();
    }

    public void showOrderMenus() {
        ordersView.showOrderMenus();
    }

    public void showEventPreview() {
        benefitDetailsView.showEventPreview();
    }

//    public void takeReservation() {
//        OutputView.println(START_MESSAGE.get());
//        OutputView.println(EXPECTED_VISIT_DATE.get());
//        int date = inputVisitDate();
//
//        OutputView.println(ORDER_MENU.get());
//        inputOrders();
//        OutputView.println(SHOW_EVENT.get(date));
//    }
//
//    public int inputVisitDate() {
//        int date = InputView.inputNumber();
//        eventCalendar.takeReservation(date);
//
//        return date;
//    }
//
//    public void inputOrders() {
//        String inputOrders = InputView.input();
//        orders.takeOrders(inputOrders);
//    }
//
//    public void showOrderMenus() {
//        OutputView.println(TitleMessage.ORDER_MENU.get());
//        OutputView.println(orders.generateOrderedMenu());
//    }
//
//    public void calculateSale() {
//        benefitDetails.categorizeTotalBenefit();
//
//        int totalPrice = benefitDetails.calculateTotalPrice();
//
//        OutputView.println(BEFORE_SALE_PRICE.get());
//        OutputView.println(getPriceMessage(totalPrice));
//
//        Giveaway giveaway = benefitDetails.calculateFreeGiftPrice();
//
//        OutputView.println(GIVEAWAY_MENU.get());
//        OutputView.println(giveaway.getProduct());
//
//        OutputView.println(BENEFIT.get());
//        String benefitDetails = this.benefitDetails.getTotalBenefitDetails();
//        OutputView.println(benefitDetails);
//
//        int totalBenefitAmount = this.benefitDetails.calculateTotalBenefit();
//        OutputView.println(TOTAL_BENEFIT_AMOUNT.get());
//        OutputView.println(getDiscountPriceMessage(totalBenefitAmount));
//
//        OutputView.println(AFTER_SALE_PRICE.get());
//        int totalFinalPrice = this.benefitDetails.calculateFinalAmount();
//        OutputView.println(getDiscountPriceMessage(totalFinalPrice));
//
//        String badge = Badge.getBadge(this.benefitDetails);
//
//        OutputView.println(EVENT_BADGE.get());
//        OutputView.println(badge);
//    }
}
