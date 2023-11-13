package christmas.domain.calendar;

import christmas.domain.calendar.enums.Week;

import static christmas.global.exception.DateExceptionMessage.INVALID_DATE;

public class EventCalendar {
    private static final int FIRST_DAY = 1;
    private static final int THIRTY_ONE_DAY = 31;

    private int date;

    public void takeReservation(int date) {
        validateDateIsInRange(date);
        this.date = date;
    }

    private void validateDateIsInRange(int date) {
        if (date < FIRST_DAY || date > THIRTY_ONE_DAY) {
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
