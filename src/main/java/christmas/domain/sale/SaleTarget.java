package christmas.domain.sale;

import christmas.domain.calendar.Week;
import christmas.domain.order.food.FoodCategory;

import java.util.Arrays;

import static christmas.domain.calendar.Week.WEEKDAY;
import static christmas.domain.calendar.Week.WEEKEND;
import static christmas.domain.order.food.FoodCategory.DESSERT;
import static christmas.domain.order.food.FoodCategory.MAIN_DISH;

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
