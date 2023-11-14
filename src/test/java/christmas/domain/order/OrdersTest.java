package christmas.domain.order;

import christmas.domain.order.food.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        orders = new Orders();
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
    void 주문_중복시_예외처리_테스트() {
        String input = "양송이수프-2,티본스테이크-1,양송이수프-1";

        assertThatThrownBy(() -> orders.takeOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDERS.get());
    }
}