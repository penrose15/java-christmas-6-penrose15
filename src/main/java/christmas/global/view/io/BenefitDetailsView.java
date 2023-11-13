package christmas.global.view.io;

import christmas.domain.badge.Enum.EventBadge;
import christmas.domain.sale.BenefitDetails;
import christmas.domain.sale.Giveaway;
import christmas.global.view.output.OutputView;

import static christmas.global.view.message.PriceMessage.getDiscountPriceMessage;
import static christmas.global.view.message.PriceMessage.getPriceMessage;
import static christmas.global.view.message.TitleMessage.*;

public class BenefitDetailsView implements InteractionRepeatable{
    private final BenefitDetails benefitDetails;

    public BenefitDetailsView(BenefitDetails benefitDetails) {
        this.benefitDetails = benefitDetails;
    }

    public void showEventPreview() {
        calculateTotalPrice();
        showFreeGift();
        showTotalBenefitDetails();
        showTotalBenefitAmount();
        showFinalTotalPrice();
        showBadge();
    }

    public void calculateTotalPrice() {
        benefitDetails.calculateTotalPrice();
        benefitDetails.calculateTotalSaleAmount();

        int totalPrice = benefitDetails.getTotalPrice();

        OutputView.println(BEFORE_SALE_PRICE.get());
        OutputView.println(getPriceMessage(totalPrice));
    }

    public void showFreeGift() {
        Giveaway giveaway = benefitDetails.calculateFreeGiftPrice();

        OutputView.println(GIVEAWAY_MENU.get());
        OutputView.println(giveaway.getProduct());
        OutputView.println("");
    }

    public void showTotalBenefitDetails() {
        OutputView.println(BENEFIT.get());
        String benefitDetails = this.benefitDetails.getTotalBenefitMessage();
        OutputView.println(benefitDetails);
    }

    public void showTotalBenefitAmount() {
        benefitDetails.calculateTotalBenefitAmount();
        int totalBenefitAmount = benefitDetails.getTotalBenefitAmount();
        OutputView.println(TOTAL_BENEFIT_AMOUNT.get());
        OutputView.println(getDiscountPriceMessage(totalBenefitAmount));
    }

    public void showFinalTotalPrice() {
        OutputView.println(AFTER_SALE_PRICE.get());
        int totalFinalPrice = this.benefitDetails.calculateFinalPrice();
        OutputView.println(getPriceMessage(totalFinalPrice));
    }

    public void showBadge() {
        int totalBenefitAmount = benefitDetails.getTotalBenefitAmount();

        String badge = EventBadge.getBadge(totalBenefitAmount);
        OutputView.println(EVENT_BADGE.get());
        OutputView.println(badge);
    }
}
