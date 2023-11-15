package christmas.domain.sale.enums;

import christmas.domain.calendar.enums.Week;
import christmas.domain.order.enums.food.FoodCategory;

import java.util.Arrays;

import static christmas.domain.calendar.enums.Week.WEEKDAY;
import static christmas.domain.calendar.enums.Week.WEEKEND;
import static christmas.domain.order.enums.food.FoodCategory.DESSERT;
import static christmas.domain.order.enums.food.FoodCategory.MAIN_DISH;

public enum SaleTarget {
    MAIN_FOR_SALE(WEEKEND, MAIN_DISH),
    DESSERT_FOR_SALE(WEEKDAY, DESSERT);

    private final Week week;
    private final FoodCategory foodCategory;

    SaleTarget(Week week, FoodCategory foodCategory) {
        this.week = week;
        this.foodCategory = foodCategory;
    }

    public static FoodCategory findByDay(Week week) {
        return Arrays.stream(SaleTarget.values())
                .filter(target -> target.week.equals(week))
                .findFirst()
                .map(saleTarget -> saleTarget.foodCategory)
                .get();
    }
}
