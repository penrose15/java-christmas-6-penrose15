package christmas.domain.sale;

import java.util.EnumMap;
import java.util.Map;

import static christmas.domain.sale.BenefitCategory.*;

public class BenefitDetails {
    private static final int MINIMUM_TOTAL_PRICE = 10_000;
    private int totalSaleAmount;

    private final SaleDetails saleDetails;
    private final Map<BenefitCategory, Integer> benefitCategoryMap;

    public BenefitDetails(SaleDetails saleDetails) {
        this.saleDetails = saleDetails;
        benefitCategoryMap = new EnumMap<>(BenefitCategory.class);
    }

    public int calculateFinalAmount() {
        int totalPrice = calculateTotalPrice();
        return totalPrice - totalSaleAmount;
    }

    public int calculateTotalBenefit() {
        calculateTotalSales();
        Giveaway freeGift = calculateFreeGiftPrice();
        int freeGiftPrice = freeGift.getProductPrice();
        return totalSaleAmount + freeGiftPrice;
    }

    public void categorizeTotalBenefit() {
        if (checkTotalPrice()) return;
        saleDetails.categorizeSaleAmount(benefitCategoryMap);
        categorizeGiveawayPrice(benefitCategoryMap);
    }

    public void calculateTotalSales() {
        if (checkTotalPrice()) return;
        int totalSale = 0;
        for (BenefitCategory benefitCategory : benefitCategoryMap.keySet()) {
            if(benefitCategory.equals(GIVEAWAY_EVENT)) continue;
            int saleAmount = benefitCategoryMap.getOrDefault(benefitCategory, 0);
            totalSale += saleAmount;
        }
        totalSaleAmount = totalSale;

    }

    public Giveaway calculateFreeGiftPrice() {
        int totalPrice = calculateTotalPrice();
        return Giveaway.isGiveawayTarget(totalPrice);
    }

    public String getTotalBenefitDetails() {
        return BenefitCategory.getBenefitDetailMessage(benefitCategoryMap);
    }

    private void categorizeGiveawayPrice(Map<BenefitCategory, Integer> benefitCategoryMap) {
        Giveaway freeGift = calculateFreeGiftPrice();
        int freeGiftPrice = freeGift.getProductPrice();

        benefitCategoryMap.put(GIVEAWAY_EVENT, freeGiftPrice);
    }

    public int calculateTotalPrice() {
        return saleDetails.calculateTotalPrice();
    }

    private boolean checkTotalPrice() {
        int totalPrice = calculateTotalPrice();
        if(totalPrice < MINIMUM_TOTAL_PRICE) {
            totalSaleAmount = 0;
            return true;
        }
        return false;
    }
}
