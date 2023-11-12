package christmas.domain.sale;

import christmas.domain.calendar.EventCalendar;
import christmas.domain.calendar.Week;
import christmas.domain.order.Orders;
import christmas.domain.order.food.FoodCategory;

import java.util.Map;

import static christmas.domain.sale.BenefitCategory.CHRISTMAS_DDAY_SALE;
import static christmas.domain.sale.BenefitCategory.SPECIAL_SALE;

public class SaleDetails {
    private static final int SALE_PER_MENU = 2_023;
    private static final int STAR_DATE_SALE = 1_000;


    private final Orders orders;
    private final EventCalendar eventCalendar;
    private final ChristmasDDaySale christmasDDaySale;


    public SaleDetails(Orders orders, EventCalendar eventCalendar, ChristmasDDaySale christmasDDaySale) {
        this.orders = orders;
        this.eventCalendar = eventCalendar;
        this.christmasDDaySale = christmasDDaySale;
    }

    public int calculateTotalPrice() {
        return orders.calculateTotalPrice();
    }

    public Map<BenefitCategory, Integer> categorizeSaleAmount(Map<BenefitCategory, Integer> benefitCategoryMap) {
        calculateWeekSalePrice(benefitCategoryMap);
        calculateChristmasDdayEventSale(benefitCategoryMap);
        calculateStarDateSalePrice(benefitCategoryMap);

        return benefitCategoryMap;
    }

    private void calculateWeekSalePrice(Map<BenefitCategory, Integer> benefitCategoryMap) {
        Map<FoodCategory, Integer> foodCategoryMap = orders.sortByFoodCategory();

        Week week = eventCalendar.checkWeekOrWeekend();
        FoodCategory saleTarget = SaleTarget.findByDay(week);

        int saleCount = foodCategoryMap.getOrDefault(saleTarget, 0);
        int saleAmount = saleCount * SALE_PER_MENU;

        benefitCategoryMap.put(week.getBenefitCategory(), saleAmount);
    }

    private void calculateChristmasDdayEventSale(Map<BenefitCategory, Integer> benefitCategoryMap) {
        int date = eventCalendar.getDate();
        int christmasDdaySaleAmount = christmasDDaySale.calculateSale(date);

        benefitCategoryMap.put(CHRISTMAS_DDAY_SALE, christmasDdaySaleAmount);
    }

    private void calculateStarDateSalePrice(Map<BenefitCategory, Integer> benefitCategoryMap) {
        int starDateSalePrice = 0;
        boolean isStarDate = eventCalendar.checkStarDate();
        if(isStarDate) {
            starDateSalePrice += STAR_DATE_SALE;
        }
        benefitCategoryMap.put(SPECIAL_SALE, starDateSalePrice);
    }
}
