package christmas.domain.calendar.dto;

import static christmas.global.exception.DateExceptionMessage.INVALID_DATE;

public record DateDto(String date) {

    public DateDto {
        validateDateIsNotNull(date);
        validateDateIsNumber(date);
    }

    private void validateDateIsNotNull(String date) {
        if (date == null) {
            throw new IllegalArgumentException(INVALID_DATE.get());
        }
    }

    private void validateDateIsNumber(String date) {
        try {
            Integer.parseInt(date);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE.get());
        }
    }
}
