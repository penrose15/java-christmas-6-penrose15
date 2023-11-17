package christmas.domain.calendar.enums;

import christmas.domain.sale.enums.BenefitCategory;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static christmas.domain.sale.enums.BenefitCategory.WEEKDAY_SALE;
import static christmas.domain.sale.enums.BenefitCategory.WEEKEND_SALE;
import static christmas.domain.calendar.enums.YearMonthDateDefinition.*;

public enum Week {
    WEEKDAY(WEEKDAY_SALE),
    WEEKEND(WEEKEND_SALE);


    private final BenefitCategory benefitCategory;

    Week(BenefitCategory benefitCategory) {
        this.benefitCategory = benefitCategory;
    }

    public static Week determineWeek(int date) {
        LocalDate localDate = LocalDate.of(YEAR.get(), MONTH.get(), date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int week = dayOfWeek.getValue();

        if (week >= WEEKEND_START.get() && week <= WEEKEND_END.get()) {
            return WEEKEND;
        }
        return WEEKDAY;
    }


    public BenefitCategory getBenefitCategory() {
        return benefitCategory;
    }
}
