package christmas.domain.sale;

public class ChristmasDDaySale {
    private static final int BASIC_DISCOUNT = 1_000;
    private static final int SALE_PER_DATE = 100;

    public static int calculateSale(int date) {
        if(!dateIsInChristmasDDayEvent(date)) {
            return 0;
        }
        return BASIC_DISCOUNT + SALE_PER_DATE * (date - 1);
    }

    private static boolean dateIsInChristmasDDayEvent(int date) {
        return date <= 25;
    }
}
