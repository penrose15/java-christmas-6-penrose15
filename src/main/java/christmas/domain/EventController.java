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
        showOrderedMenus();
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

    public void showOrderedMenus() {
        ordersView.showOrderMenus();
    }

    public void showEventPreview() {
        benefitDetailsView.showEventPreview();
    }

}
