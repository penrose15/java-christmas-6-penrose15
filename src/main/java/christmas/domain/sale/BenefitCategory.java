package christmas.domain.sale;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

public enum BenefitCategory {
    CHRISTMAS_DDAY_SALE("크리스마스 디데이 할인"),
    WEEKDAY_SALE("평일 할인"),
    WEEKEND_SALE("주말 할인"),
    SPECIAL_SALE("특별 할인"),
    GIVEAWAY_EVENT("증정 이벤트");

    private final String saleName;

    BenefitCategory(String saleName) {
        this.saleName = saleName;
    }

    public static String getBenefitDetailMessage(Map<BenefitCategory, Integer> benefitCategoryMap) {
        StringBuilder detailMessage = new StringBuilder();
        NumberFormat numberFormat = NumberFormat.getInstance();

        for (BenefitCategory benefitCategory : benefitCategoryMap.keySet()) {
            int salePrice = benefitCategoryMap.getOrDefault(benefitCategory, 0);
            if(salePrice == 0) continue;
            String cost = numberFormat.format(salePrice);
            String message = String.format("%s: -%s원\n",benefitCategory.saleName, cost);
            detailMessage.append(message);
        }
        return detailMessage.toString();
    }
}
