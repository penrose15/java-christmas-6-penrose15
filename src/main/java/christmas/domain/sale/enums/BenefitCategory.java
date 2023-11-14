package christmas.domain.sale.enums;

import java.text.NumberFormat;
import java.util.Map;

public enum BenefitCategory {
    CHRISTMAS_DDAY_SALE("크리스마스 디데이 할인"),
    WEEKDAY_SALE("평일 할인"),
    WEEKEND_SALE("주말 할인"),
    SPECIAL_SALE("특별 할인"),
    GIVEAWAY_EVENT("증정 이벤트");

    private static final String NO_BENEFIT = "없음";
    private static final String PRICE_FORMAT = "%s: -%s원\n";

    private final String saleName;

    BenefitCategory(String saleName) {
        this.saleName = saleName;
    }

    public static String getBenefitDetailMessage(Map<BenefitCategory, Integer> benefitCategoryMap) {
        String detailMessage = makeBenefitDetails(benefitCategoryMap);

        if (detailMessage.isEmpty()) {
            return NO_BENEFIT;
        }

        return detailMessage;
    }

    private static String makeBenefitDetails(Map<BenefitCategory, Integer> benefitCategoryMap) {
        StringBuilder detailMessage = new StringBuilder();

        for (BenefitCategory benefitCategory : benefitCategoryMap.keySet()) {
            int salePrice = benefitCategoryMap.getOrDefault(benefitCategory, 0);
            if (salePrice == 0) continue;

            String message = makeBenefitDetail(benefitCategory, salePrice);
            detailMessage.append(message);
        }
        return detailMessage.toString();
    }

    private static String makeBenefitDetail(BenefitCategory benefitCategory, int salePrice) {
        NumberFormat numberFormat = NumberFormat.getInstance();

        String cost = numberFormat.format(salePrice);
        return String.format(PRICE_FORMAT, benefitCategory.saleName, cost);
    }
}
