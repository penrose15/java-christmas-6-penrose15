package christmas.domain.calendar;

import christmas.domain.calendar.enums.Week;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static christmas.domain.calendar.enums.Week.WEEKDAY;
import static christmas.domain.calendar.enums.Week.WEEKEND;
import static christmas.global.exception.DateExceptionMessage.INVALID_DATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class EventCalendarTest {

    EventCalendar eventCalendar;
    List<Integer> starDates = List.of(3,10,17,24,25,31);


    @BeforeEach
    void setUp() {
        eventCalendar = EventCalendar.getInstance();
    }

    @Test
    void 날짜가_1일_미만인_경우_예외_처리_테스트() {
        String date = "0";
        assertThatThrownBy(() -> eventCalendar.takeReservation(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE.get());
    }

    @Test
    void 날짜가_31일_초과인_경우_예외_처리_테스트() {
        String date = "32";
        assertThatThrownBy(() -> eventCalendar.takeReservation(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE.get());
    }

    @Test
    void 날짜가_주말이라면_주말이라고_판별() {
        eventCalendar.takeReservation("1");
        Week week = eventCalendar.checkWeekOrWeekend();

        assertThat(week)
                .isEqualTo(WEEKEND);
    }

    @Test
    void 날짜가_평일이라면_평일이라고_판별() {
        eventCalendar.takeReservation("4");
        Week week = eventCalendar.checkWeekOrWeekend();

        assertThat(week)
                .isEqualTo(WEEKDAY);
    }

    @Test
    void STAR_DATE_일때_STAR_DATE라고_판별하는지_테스트() {


        for (Integer starDate : starDates) {
            eventCalendar.takeReservation(starDate.toString());
            boolean isStarDate = eventCalendar.checkStarDate();

            assertThat(isStarDate)
                    .isTrue();
        }
    }

    @Test
    void STAR_DATE가_아닐때_STAR_DATE가_아니라고_판별하는지_테스트() {
        List<Integer> notStarDates = new ArrayList<>();

        for(int i = 1; i<=31; i++) {
            if(!starDates.contains(i)) {
                notStarDates.add(i);
            }
        }

        for (Integer starDate : notStarDates) {
            eventCalendar.takeReservation(starDate.toString());
            boolean isStarDate = eventCalendar.checkStarDate();

            assertThat(isStarDate)
                    .isFalse();
        }
    }


}