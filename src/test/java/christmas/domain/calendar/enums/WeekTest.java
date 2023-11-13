package christmas.domain.calendar.enums;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static christmas.domain.calendar.enums.Week.WEEKDAY;
import static christmas.domain.calendar.enums.Week.WEEKEND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WeekTest {

    @Test
    void 주말인지_판별_테스트() {
        int date = 1;

        Week week = Week.determineWeek(date);
        assertThat(week)
                .isEqualTo(WEEKEND);
    }

    @Test
    void 평일인지_판별_테스트() {
        int date = 4;

        Week week = Week.determineWeek(date);
        assertThat(week)
                .isEqualTo(WEEKDAY);
    }

}