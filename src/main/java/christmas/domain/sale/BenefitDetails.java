package christmas.domain.sale;

import christmas.domain.sale.enums.BenefitCategory;
import christmas.domain.sale.enums.Giveaway;

import java.util.Map;

public class BenefitDetails {
    private final String NO_BENEFIT = "없음\n";

    int totalPrice;
    int totalSaleAmount;
    private final SaleDetails saleDetails;

    public BenefitDetails(SaleDetails saleDetails) {
        this.saleDetails = saleDetails;
    }

    public void calculateTotalPrice() {
        this.totalPrice = saleDetails.calculateTotalPrice();
    }

    public void calculateTotalSaleAmount() {
        this.totalSaleAmount = saleDetails.calculateTotalSaleAmount();
    }

    public int calculateFinalPrice() {
        return totalPrice - totalSaleAmount;
    }

    public int calculateTotalBenefitAmount() {
        Giveaway giveaway = calculateFreeGiftPrice();

        int freeGiftPrice = giveaway.getProductPrice();
        return totalSaleAmount + freeGiftPrice;
    }

    public String getTotalBenefitMessage() {
        if(totalPrice < 10_000) {
            return NO_BENEFIT;
        }

        Map<BenefitCategory, Integer> benefitCategoryMap = saleDetails.categorizeSaleAmount();
        Giveaway giveaway = calculateFreeGiftPrice();
        benefitCategoryMap.put(BenefitCategory.GIVEAWAY_EVENT, giveaway.getProductPrice());

        return BenefitCategory.getBenefitDetailMessage(benefitCategoryMap);
    }

    public Giveaway calculateFreeGiftPrice() {
        return Giveaway.isGiveawayTarget(totalPrice);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getTotalSaleAmount() {
        return totalSaleAmount;
    }
}
