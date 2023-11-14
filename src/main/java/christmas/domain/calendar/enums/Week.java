package christmas.domain.calendar.enums;

import christmas.domain.sale.enums.BenefitCategory;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static christmas.domain.sale.enums.BenefitCategory.WEEKDAY_SALE;
import static christmas.domain.sale.enums.BenefitCategory.WEEKEND_SALE;

public enum Week {
    WEEKDAY(WEEKDAY_SALE),
    WEEKEND(WEEKEND_SALE);

    private final BenefitCategory benefitCategory;

    Week(BenefitCategory benefitCategory) {
        this.benefitCategory = benefitCategory;
    }

    public static Week determineWeek(int date) {
        LocalDate localDate = LocalDate.of(2023,12,date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int week = dayOfWeek.getValue();

        if(week >= 5 && week < 7) {
            return WEEKEND;
        }
        return WEEKDAY;
    }


    public BenefitCategory getBenefitCategory() {
        return benefitCategory;
    }
}
