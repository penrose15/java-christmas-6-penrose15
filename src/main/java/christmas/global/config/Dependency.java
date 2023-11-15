package christmas.global.config;

import christmas.domain.EventController;
import christmas.domain.calendar.EventCalendar;
import christmas.domain.order.Orders;
import christmas.domain.sale.BenefitDetails;
import christmas.domain.sale.ChristmasDDaySale;
import christmas.domain.sale.SaleDetails;
import christmas.global.view.io.BenefitDetailsView;
import christmas.global.view.io.EventCalendarView;
import christmas.global.view.io.OrdersView;

public class Dependency {
    public static EventController eventController() {
        return new EventController(eventCalendarView(), ordersView(), benefitDetailsView());
    }

    public static BenefitDetailsView benefitDetailsView() {
        return new BenefitDetailsView(benefitDetails());
    }

    public static OrdersView ordersView() {
        return new OrdersView(Orders.getInstance());
    }

    public static EventCalendarView eventCalendarView() {
        return new EventCalendarView(EventCalendar.getInstance());
    }

    public static BenefitDetails benefitDetails() {
        return new BenefitDetails(saleDetails());
    }

    public static SaleDetails saleDetails() {
        return new SaleDetails(Orders.getInstance(), EventCalendar.getInstance(), ChristmasDDaySale.getInstance());
    }

}
