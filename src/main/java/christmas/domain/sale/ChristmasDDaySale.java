package christmas.domain.sale;

import static christmas.global.exception.DateExceptionMessage.INVALID_DATE;

public class ChristmasDDaySale {
    private static final int BASIC_DISCOUNT = 1_000;
    private static final int SALE_PER_DATE = 100;

    public int calculateSale(int date) {
        if(date > 25) {
            return 0;
        }
        return BASIC_DISCOUNT + SALE_PER_DATE * (date - 1);
    }
}
