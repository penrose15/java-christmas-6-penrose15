package christmas.domain.calendar;

import christmas.domain.sale.ChristmasDDaySale;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static christmas.global.exception.DateExceptionMessage.INVALID_DATE;

public class EventCalendar {
    private int date;
    private LocalDate reservationDate;

    public void takeReservation(int date) {
        validateDateIsInRange(date);
        this.date = date;
        reservationDate = toLocalDate(date);
    }

    private void validateDateIsInRange(int date) {
        if(date < 0 || date > 31) {
            throw new IllegalArgumentException(INVALID_DATE.get());
        }
    }

    private LocalDate toLocalDate(int date) {
        return LocalDate.of(2023,12,date);
    }

    public Week checkWeekOrWeekend() {
        int dayOfWeek = getDayOfWeek();
        return Week.determineWeek(dayOfWeek);
    }

    private int getDayOfWeek() {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        return dayOfWeek.getValue();
    }

    public int calculateChristmasDDayEventSale() {
        return ChristmasDDaySale.calculateSale(date);
    }

    public boolean checkStarDate() {
        int dayOfWeek = getDayOfWeek();
        StarDate starDate = StarDate.checkStarDate(dayOfWeek);
        return starDate.equals(StarDate.STAR);
    }

    public int getDate() {
        return date;
    }
}
