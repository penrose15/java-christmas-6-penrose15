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
import org.junit.jupiter.api.Order;

public class Dependency {
    public static EventController eventController() {
        Orders orders = orders();
        EventCalendar eventCalendar = eventCalendar();

        SaleDetails saleDetails = saleDetails(orders, eventCalendar, christmasDDaySale());

        return new EventController(eventCalendarView(eventCalendar),
                ordersView(orders),
                benefitDetailsView(benefitDetails(saleDetails)));
    }

    public static BenefitDetailsView benefitDetailsView(BenefitDetails benefitDetails) {
        return new BenefitDetailsView(benefitDetails);
    }

    public static OrdersView ordersView(Orders orders) {
        return new OrdersView(orders);
    }

    public static EventCalendarView eventCalendarView(EventCalendar eventCalendar) {
        return new EventCalendarView(eventCalendar);
    }

    public static BenefitDetails benefitDetails(SaleDetails saleDetails) {
        return new BenefitDetails(saleDetails);
    }

    public static SaleDetails saleDetails(Orders orders, EventCalendar eventCalendar, ChristmasDDaySale christmasDDaySale) {
        return new SaleDetails(orders, eventCalendar, christmasDDaySale);
    }

    public static ChristmasDDaySale christmasDDaySale() {
        return new ChristmasDDaySale();
    }

    public static EventCalendar eventCalendar() {
        return new EventCalendar();
    }

    public static Orders orders() {
        return new Orders();
    }
}
