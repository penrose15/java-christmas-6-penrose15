package christmas.domain.calendar;

public enum StarDate {
    STAR,
    NOT_STAR;

    public static StarDate checkStarDate(int number) {
        if(number == 7) {
            return STAR;
        }
        return NOT_STAR;
    }
}
