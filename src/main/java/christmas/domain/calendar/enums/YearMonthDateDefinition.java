package christmas.domain.calendar.enums;

public enum YearMonthDateDefinition {
    YEAR(2023),
    MONTH(12),
    WEEKEND_START(5),
    WEEKEND_END(6),
    FIRST_DATE(1),
    LAST_DATE(31)
    ;

    private final int number;

    YearMonthDateDefinition(int number) {
        this.number = number;
    }

    public int get() {
        return number;
    }
}
