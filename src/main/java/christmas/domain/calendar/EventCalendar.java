package christmas.domain.calendar;

import christmas.domain.calendar.enums.Week;
import christmas.domain.calendar.converter.DateConverter;

import static christmas.global.exception.DateExceptionMessage.INVALID_DATE;
import static christmas.domain.calendar.enums.YearMonthDateDefinition.FIRST_DATE;
import static christmas.domain.calendar.enums.YearMonthDateDefinition.LAST_DATE;

public class EventCalendar {

    private int date;

    private static final EventCalendar INSTANCE = new EventCalendar();

    private EventCalendar() {
    }

    public static EventCalendar getInstance() {
        return INSTANCE;
    }

    public int takeReservation(String date) {
        this.date = DateConverter.convertToDate(date);
        validateDateIsInRange(this.date);

        return this.date;
    }

    private void validateDateIsInRange(int date) {
        if (date < FIRST_DATE.get() || date > LAST_DATE.get()) {
            throw new IllegalArgumentException(INVALID_DATE.get());
        }
    }


    public Week checkWeekOrWeekend() {
        return Week.determineWeek(date);
    }


    public boolean checkStarDate() {
        return StarDate.checkStarDate(date);
    }

    public int getDate() {
        return date;
    }
}
