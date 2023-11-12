package christmas.domain;

import christmas.domain.badge.Badge;
import christmas.domain.calendar.EventCalendar;
import christmas.domain.order.Orders;
import christmas.domain.sale.Giveaway;
import christmas.domain.sale.Sale;
import christmas.global.view.input.InputView;
import christmas.global.view.output.OutputView;
import christmas.global.view.message.OrderDynamicMessage;
import christmas.global.view.message.OrderStaticMessage;
import christmas.global.view.message.TitleMessage;

import static christmas.global.view.message.PriceMessage.getDiscountPriceMessage;
import static christmas.global.view.message.PriceMessage.getPriceMessage;
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
        OutputView.println(OrderStaticMessage.START_MESSAGE.get());
        OutputView.println(OrderStaticMessage.EXPECTED_VISIT_DATE.get());
        inputVisitDate();

        OutputView.println(OrderStaticMessage.ORDER_MENU.get());
        inputOrders();
        OutputView.println(OrderDynamicMessage.SHOW_EVENT.get(eventCalendar.getDate()));


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
        OutputView.println(TitleMessage.ORDER_MENU.get());
        OutputView.println(orders.generateOrderedMenu());
    }

    public void calculateSale() {
        sale.categorizeTotalBenefit();

        int totalPrice = sale.calculateTotalPrice();

        OutputView.println(BEFORE_SALE_PRICE.get());
        OutputView.println(getPriceMessage(totalPrice));

        Giveaway giveaway = sale.isGiftTarget();

        OutputView.println(GIVEAWAY_MENU.get());
        OutputView.println(giveaway.getProduct());

        OutputView.println(BENEFIT.get());
        String benefitDetails = sale.getTotalBenefitDetails();
        OutputView.println(benefitDetails);

        int totalBenefitAmount = sale.totalBenefitAmount();
        OutputView.println(TOTAL_BENEFIT_AMOUNT.get());
        OutputView.println(getDiscountPriceMessage(totalBenefitAmount));

        OutputView.println(AFTER_SALE_PRICE.get());
        int totalFinalPrice = sale.calculateFinalAmount();
        OutputView.println(getDiscountPriceMessage(totalFinalPrice));

        String badge = Badge.getBadge(sale);

        OutputView.println(EVENT_BADGE.get());
        OutputView.println(badge);
    }
}
