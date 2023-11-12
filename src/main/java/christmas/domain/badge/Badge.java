package christmas.domain.badge;

import christmas.domain.badge.Enum.EventBadge;
import christmas.domain.sale.Sale;

public class Badge {

    public static String getBadge(Sale sale) {
        int totalBenefitPrice = sale.totalBenefitAmount();
        return EventBadge.getBadge(totalBenefitPrice);
    }
}
