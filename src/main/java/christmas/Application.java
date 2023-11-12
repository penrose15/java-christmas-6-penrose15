package christmas;

import christmas.domain.EventController;
import christmas.domain.calendar.EventCalendar;
import christmas.domain.order.Orders;
import christmas.domain.sale.Sale;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Orders orders = new Orders();
        EventCalendar eventCalendar = new EventCalendar();

        EventController eventController = new EventController(orders, eventCalendar);

        eventController.play();
    }
}
