package christmas.domain.order.dto;

import org.junit.jupiter.api.Test;

import static christmas.global.exception.OrderExceptionMessage.INVALID_ORDERS;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrdersDtoTest {

    @Test
    void 수량이_숫자가_아닌경우_예외처리_테스트() {
        String input = "양송이수프-2,티본스테이크-a,제로콜라-1";

        assertThatThrownBy(() -> new OrdersDto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDERS.get());
    }

    @Test
    void 주문형식이_올바르지_않는_경우_예외처리_테스트1() {
        String input = ",양송이수프-2,티본스테이크-2,제로콜라-1";

        assertThatThrownBy(() -> new OrdersDto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDERS.get());
    }

    @Test
    void 주문형식이_올바르지_않는_경우_예외처리_테스트2() {
        String input = "양송이수프-2,티본스테이크-2,제로콜라-1,";

        assertThatThrownBy(() -> new OrdersDto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDERS.get());
    }

    @Test
    void 수량이_하이픈으로_구분되지_않는_경우_예외처리_테스트2() {
        String input = "양송이수프-2,티본스테이크:2,제로콜라:1";

        assertThatThrownBy(() -> new OrdersDto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDERS.get());
    }
}
