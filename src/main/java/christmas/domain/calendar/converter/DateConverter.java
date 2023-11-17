package christmas.domain.calendar.converter;

import static christmas.global.exception.DateExceptionMessage.INVALID_DATE;

public class DateConverter {
    public static int convertToDate(String inputNumber) {
        try {
            return Integer.parseInt(inputNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE.get());
        }
    }
}
