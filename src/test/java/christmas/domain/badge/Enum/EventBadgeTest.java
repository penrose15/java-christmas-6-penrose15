package christmas.domain.badge.Enum;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class EventBadgeTest {

    @Test
    void 총혜택금액_0원미만_예외처리_테스트() {
        assertThatThrownBy(() -> EventBadge.getBadge(-100))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 총혜택금액_5000원이하_없음_테스트() {
        String badge = EventBadge.getBadge(4900);

        assertThat(badge)
                .isEqualTo("없음");
    }

    @Test
    void 총혜택금액_5000원이상_10000원미만_별_테스트() {
        String badge = EventBadge.getBadge(9900);

        assertThat(badge)
                .isEqualTo("별");
    }

    @Test
    void 총혜택금액_10000원이상_20000원미만_트리_테스트() {
        String badge = EventBadge.getBadge(19900);

        assertThat(badge)
                .isEqualTo("트리");
    }

    @Test
    void 총혜택금액_20000원이상_산타_테스트() {
        String badge = EventBadge.getBadge(20000);

        assertThat(badge)
                .isEqualTo("산타");
    }

}