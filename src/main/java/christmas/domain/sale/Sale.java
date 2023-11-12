package christmas.domain.sale;

import christmas.domain.calendar.EventCalendar;
import christmas.domain.calendar.Week;
import christmas.domain.order.Orders;
import christmas.domain.order.food.FoodCategory;
import org.junit.jupiter.api.BeforeEach;

import java.util.EnumMap;
import java.util.Map;

import static christmas.domain.calendar.Week.WEEKDAY;
import static christmas.domain.order.food.Food.샴페인;
import static christmas.domain.sale.BenefitCategory.*;

public class Sale {
    private static final int MINIMUM_TOTAL_PRICE = 10_000;
    private static final int SALE_PER_MENU = 2_023;
    private static final int STAR_DATE_SALE = 1_000;

    private final Orders orders;
    private final EventCalendar eventCalendar;
    private final Map<BenefitCategory, Integer> benefitCategoryMap;

    public Sale(Orders orders, EventCalendar eventCalendar) {
        this.orders = orders;
        this.eventCalendar = eventCalendar;
        benefitCategoryMap = new EnumMap<>(BenefitCategory.class);
    }

    public int calculateFinalAmount() {
        int totalPrice = calculateTotalPrice();
        int totalSaleAmount = calculateTotalSales();

        return totalPrice - totalSaleAmount;
    }

    public int totalBenefitAmount() {
        int totalSaleAmount = calculateTotalSales();
        int freeGiftPrice = calculateGiveawayPrice();
        return totalSaleAmount + freeGiftPrice;
    }

    private int calculateGiveawayPrice() {
        Giveaway freeGift = isGiftTarget();
        return freeGift.getProductPrice();
    }

    public String getTotalBenefitDetails() {
        return BenefitCategory.getBenefitDetailMessage(benefitCategoryMap);
    }

    public void categorizeTotalBenefit() {
        int christmasDDaySalePrice = calculateChristmasDDaySalePrice();
        benefitCategoryMap.put(CHRISTMAS_DDAY_SALE, christmasDDaySalePrice);
        int weekSalePrice = calculateWeekSalePrice();
        Week week = eventCalendar.checkWeekOrWeekend();
        benefitCategoryMap.put(week.determineBenefitCategory(), weekSalePrice);

        int starDateSalePrice = calculateStarDateSalePrice();
        benefitCategoryMap.put(SPECIAL_SALE, starDateSalePrice);

        int freeGiftTarget = calculateGiveawayPrice();
        benefitCategoryMap.put(GIVEAWAY_EVENT, freeGiftTarget);
    }

    public int calculateTotalSales() {
        int totalPrice = calculateTotalPrice();
        if(!checkTotalPriceMoreThanTenThousand(totalPrice)) {
            return 0;
        }
        int totalSale = 0;
        for (BenefitCategory benefitCategory : benefitCategoryMap.keySet()) {
            if(benefitCategory.equals(GIVEAWAY_EVENT)) continue;
            int saleAmount = benefitCategoryMap.getOrDefault(benefitCategory, 0);
            totalSale += saleAmount;
        }

        return totalSale;

    }

    public Giveaway isGiftTarget() {
        int totalPrice = calculateTotalPrice();
        return Giveaway.isGiveawayTarget(totalPrice);
    }

    private int calculateChristmasDDaySalePrice() {

        return eventCalendar.calculateChristmasDDayEventSale();
    }

    private int calculateWeekSalePrice() {
        Map<FoodCategory, Integer> foodCategoryMap = orders.sortByFoodCategory();

        Week week = eventCalendar.checkWeekOrWeekend();
        FoodCategory saleTarget = SaleTarget.findByDay(week);

        int saleCount = foodCategoryMap.getOrDefault(saleTarget, 0);
        return saleCount * SALE_PER_MENU;
    }

    private int calculateStarDateSalePrice() {
        int starDateSalePrice = 0;
        boolean isStarDate = eventCalendar.checkStarDate();
        if(isStarDate) {
            starDateSalePrice += STAR_DATE_SALE;
        }
        return starDateSalePrice;
    }

    public int calculateTotalPrice() {
        return orders.calculateTotalPrice();
    }

    private boolean checkTotalPriceMoreThanTenThousand(int totalPrice) {
        return totalPrice >= MINIMUM_TOTAL_PRICE;
    }
}
