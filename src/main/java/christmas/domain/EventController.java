package christmas.domain;

import christmas.domain.badge.Badge;
import christmas.domain.calendar.EventCalendar;
import christmas.domain.order.Orders;
import christmas.domain.sale.Giveaway;
import christmas.domain.sale.Sale;
import christmas.global.view.InputView;
import christmas.global.view.Output;
import christmas.global.view.OutputView;
import christmas.global.view.message.OrderDynamicMessage;
import christmas.global.view.message.OrderStaticMessage;
import christmas.global.view.message.TitleMessage;

import static christmas.global.view.message.TitleMessage.*;

public class EventController {
    private final Orders orders;
    private final EventCalendar eventCalendar;
    private final Sale sale;

    public EventController(Orders orders, EventCalendar eventCalendar) {
        this.orders = orders;
        this.eventCalendar = eventCalendar;
        this.sale = new Sale(orders, eventCalendar);
    }

    public void play() {
        takeReservation();
        showOrderMenus();
        calculateSale();
    }

    public void takeReservation() {
        OutputView.outputView(OrderStaticMessage.START_MESSAGE.get());
        OutputView.outputView(OrderStaticMessage.EXPECTED_VISIT_DATE.get());
        inputVisitDate();

        OutputView.outputView(OrderStaticMessage.ORDER_MENU.get());
        inputOrders();
        OutputView.outputView(OrderDynamicMessage.SHOW_EVENT.get(eventCalendar.getDate()));


    }

    public void inputVisitDate() {
        int date = InputView.inputNumber();
        eventCalendar.takeReservation(date);
    }

    public void inputOrders() {
        String inputOrders = InputView.input();
        orders.validateOrders(inputOrders);
    }

    public void showOrderMenus() {
        OutputView.outputView(TitleMessage.ORDER_MENU.get());
        OutputView.outputView(orders.generateOrderedMenu());
    }

    public void calculateSale() {
        sale.categorizeTotalBenefit();

        int totalPrice = sale.calculateTotalPrice();

        String beforeSaleMessage = String.format("%s원", totalPrice);
        OutputView.outputView(BEFORE_SALE_PRICE.get());
        OutputView.outputView(beforeSaleMessage);

        Giveaway giveaway = sale.isGiftTarget();

        OutputView.outputView(GIVEAWAY_MENU.get());
        OutputView.outputView(giveaway.getProduct());

        OutputView.outputView(BENEFIT.get());
        String benefitDetails = sale.getTotalBenefitDetails();
        OutputView.outputView(benefitDetails);

        int totalBenefitAmount = sale.totalBenefitAmount();
        OutputView.outputView(TOTAL_BENEFIT_AMOUNT.get());
        OutputView.outputView("-" + totalBenefitAmount + "원");

        OutputView.outputView(AFTER_SALE_PRICE.get());
        int totalFinalPrice = sale.calculateFinalAmount();
        OutputView.outputView(totalFinalPrice + "원");

        String badge = Badge.getBadge(sale);

        OutputView.outputView(EVENT_BADGE.get());
        OutputView.outputView(badge);
    }
}
