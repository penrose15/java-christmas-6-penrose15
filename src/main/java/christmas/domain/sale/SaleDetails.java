package christmas.domain.sale;

import christmas.domain.calendar.EventCalendar;
import christmas.domain.calendar.enums.Week;
import christmas.domain.order.Orders;
import christmas.domain.order.food.FoodCategory;
import christmas.domain.sale.enums.BenefitCategory;
import christmas.domain.sale.enums.SaleTarget;

import java.util.EnumMap;
import java.util.Map;

import static christmas.domain.sale.enums.BenefitCategory.*;

public class SaleDetails {
    private static final int MINIMUM_TOTAL_PRICE = 10_000;
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

    public int calculateTotalSaleAmount() {
        Map<BenefitCategory, Integer> benefitCategoryMap = categorizeSaleAmount();

        if (!checkTotalPriceMoreThanTenThousand()) return 0;

        int totalSale = 0;
        for (BenefitCategory benefitCategory : benefitCategoryMap.keySet()) {
            int saleAmount = benefitCategoryMap.getOrDefault(benefitCategory, 0);
            totalSale += saleAmount;
        }
        return totalSale;
    }

    private boolean checkTotalPriceMoreThanTenThousand() {
        int totalPrice = calculateTotalPrice();
        if (totalPrice < MINIMUM_TOTAL_PRICE) {
            return false;
        }
        return true;
    }

    public Map<BenefitCategory, Integer> categorizeSaleAmount() {
        Map<BenefitCategory, Integer> benefitCategoryMap = new EnumMap<>(BenefitCategory.class);

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
