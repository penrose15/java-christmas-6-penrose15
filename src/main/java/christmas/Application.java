package christmas;

import christmas.domain.EventController;
import christmas.domain.calendar.EventCalendar;
import christmas.domain.order.Orders;
import christmas.domain.sale.BenefitDetails;
import christmas.domain.sale.ChristmasDDaySale;
import christmas.domain.sale.SaleDetails;
import christmas.global.view.io.EventCalendarView;
import christmas.global.view.io.OrdersView;
import christmas.global.view.io.BenefitDetailsView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Orders orders = new Orders();
        EventCalendar eventCalendar = new EventCalendar();
        ChristmasDDaySale christmasDDaySale = new ChristmasDDaySale();
        SaleDetails saleDetails = new SaleDetails(orders, eventCalendar, christmasDDaySale);
        BenefitDetails benefitDetails = new BenefitDetails(saleDetails);

        EventCalendarView eventCalendarView = new EventCalendarView(eventCalendar);
        OrdersView ordersView = new OrdersView(orders);
        BenefitDetailsView benefitDetailsView = new BenefitDetailsView(benefitDetails);

        EventController eventController = new EventController(eventCalendarView, ordersView, benefitDetailsView);

        eventController.play();
    }
}
