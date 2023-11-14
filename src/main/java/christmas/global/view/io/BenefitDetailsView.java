package christmas.global.view.io;

import christmas.domain.badge.Enum.EventBadge;
import christmas.domain.sale.BenefitDetails;
import christmas.domain.sale.enums.Giveaway;
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
        showResult();
    }

    public void calculateTotalPrice() {
        benefitDetails.calculateTotalPrice();
        benefitDetails.calculateTotalSaleAmount();

        int totalPrice = benefitDetails.getTotalPrice();

        OutputView.println(BEFORE_SALE_PRICE.get());
        OutputView.println(getPriceMessage(totalPrice));
    }

    public void showResult() {
        showFreeGift();
        showTotalBenefitDetails();
        int totalBenefitAmount = showTotalBenefitAmount();
        showFinalTotalPrice();
        showBadge(totalBenefitAmount);
    }

    private void showFreeGift() {
        Giveaway giveaway = benefitDetails.calculateFreeGiftPrice();

        OutputView.println(GIVEAWAY_MENU.get());
        OutputView.println(giveaway.getProduct());
        OutputView.printNextLine();
    }

    private void showTotalBenefitDetails() {
        OutputView.println(BENEFIT.get());
        String benefitDetails = this.benefitDetails.getTotalBenefitMessage();
        OutputView.println(benefitDetails);
    }

    private int showTotalBenefitAmount() {
        int totalBenefitAmount = benefitDetails.calculateTotalBenefitAmount();
        OutputView.println(TOTAL_BENEFIT_AMOUNT.get());
        OutputView.println(getDiscountPriceMessage(totalBenefitAmount));

        return totalBenefitAmount;
    }

    private void showFinalTotalPrice() {
        OutputView.println(AFTER_SALE_PRICE.get());
        int totalFinalPrice = this.benefitDetails.calculateFinalPrice();
        OutputView.println(getPriceMessage(totalFinalPrice));
    }

    private void showBadge(int totalBenefitAmount) {
        String badge = EventBadge.getBadge(totalBenefitAmount);
        OutputView.println(EVENT_BADGE.get());
        OutputView.println(badge);
    }
}
