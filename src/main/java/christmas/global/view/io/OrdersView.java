package christmas.global.view.io;

import christmas.domain.order.Orders;
import christmas.domain.order.dto.OrdersDto;
import christmas.global.view.input.InputView;
import christmas.global.view.output.OutputView;

import static christmas.global.view.message.OrderStaticMessage.ORDER_MENU_AND_COUNT;
import static christmas.global.view.message.TitleMessage.ORDER_MENU;

public class OrdersView implements InteractionRepeatable{
    private final Orders orders;

    public OrdersView(Orders orders) {
        this.orders = orders;
    }

    public void inputOrders() {
        OutputView.println(ORDER_MENU_AND_COUNT.get());
        runInteraction(() ->  {
            String inputOrders = InputView.input();
            OrdersDto ordersDto = new OrdersDto(inputOrders);

            orders.takeOrders(ordersDto.orders());
        });
    }

    public void showOrderMenus() {
        OutputView.println(ORDER_MENU.get());
        OutputView.println(orders.generateOrderedMenu());
    }


}
