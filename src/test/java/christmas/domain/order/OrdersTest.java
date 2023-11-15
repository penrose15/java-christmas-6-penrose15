package christmas.domain.order;

import christmas.domain.order.food.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.util.List;
import java.util.Map;

import static christmas.domain.order.food.Food.*;
import static christmas.global.exception.OrderExceptionMessage.INVALID_ORDERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {

    private Orders orders;

    @BeforeEach
    void setUp() {
        orders = Orders.getInstance();
    }

    @Test
    void 주문이_잘_들어가는지_확인() {
        String input = "양송이수프-2,티본스테이크-1,제로콜라-1";

        List<Food> foods = List.of(양송이수프, 티본스테이크, 제로콜라);
        List<Integer> foodQuantity = List.of(2,1,1);

        orders.takeOrders(input);
        Map<Food, Integer> orderMap = orders.getOrderMap();

        for(int i = 0; i<foods.size(); i++) {
            Food food = foods.get(i);
            int quantity = foodQuantity.get(i);

            assertThat(orderMap.get(food))
                    .isEqualTo(quantity);
        }
    }

    @Test
    void 음식수량_20개_초과시_예외처리_테스트() {
        String input = "양송이수프-5,티본스테이크-7,제로콜라-8,레드와인-3,샴페인-9";

        assertThatThrownBy(() -> orders.takeOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDERS.get());
    }

    @Test
    void 수량이_0인경우_예외처리_테스트() {
        String input = "양송이수프-2,티본스테이크-0,양송이수프-1";

        assertThatThrownBy(() -> orders.takeOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDERS.get());
    }

    @Test
    void 주문_중복시_예외처리_테스트() {
        String input = "양송이수프-2,티본스테이크-1,양송이수프-1";

        assertThatThrownBy(() -> orders.takeOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDERS.get());
    }

    @Test
    void 음료만_주문시_예외처리_테스트() {
        String input = "레드와인-3,제로콜라-2";

        assertThatThrownBy(() -> orders.takeOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDERS.get());
    }
}