package christmas.domain.calendar;

import christmas.domain.sale.ChristmasDDaySale;

import static christmas.global.exception.DateExceptionMessage.INVALID_DATE;

public class EventCalendar {
    private int date;

    public void takeReservation(int date) {
        validateDateIsInRange(date);
        this.date = date;
    }

    private void validateDateIsInRange(int date) {
        if(date < 0 || date > 31) {
            throw new IllegalArgumentException(INVALID_DATE.get());
        }
    }

    public boolean checkChristmasIsInRange() {
        if(date > 0 && date < 25) {
            return true;
        }
        return false;
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
