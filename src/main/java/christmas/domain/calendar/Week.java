package christmas.domain.calendar;

import christmas.domain.sale.BenefitCategory;

public enum Week {
    WEEKDAY,
    WEEKEND;

    public static Week determineWeek(int number) {
        if(number > 5 && number <= 7) {
            return WEEKEND;
        }
        return WEEKDAY;
    }

    public BenefitCategory determineBenefitCategory() {
        if(this.equals(WEEKDAY)) {
            return BenefitCategory.WEEKDAY_SALE;
        }
        return BenefitCategory.WEEKEND_SALE;
    }
}
