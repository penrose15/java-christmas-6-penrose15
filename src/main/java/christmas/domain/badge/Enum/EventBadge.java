package christmas.domain.badge.Enum;

public enum EventBadge {
    STAR("별", 5_000)
    ,TREE("트리", 10_000)
    ,SANTA("산타", 20_000)
    ;

    private final String badge;
    private final int benefitPrice;

    EventBadge(String badge, int benefitPrice) {
        this.badge = badge;
        this.benefitPrice = benefitPrice;
    }

    public static String getBadge(int benefitPrice) {
        validateBenefitPrice(benefitPrice);
        if(benefitPrice >= SANTA.benefitPrice) {
            return SANTA.badge;
        }
        if(benefitPrice >= TREE.benefitPrice) {
            return TREE.badge;
        }
        return STAR.badge;
    }

    public static void validateBenefitPrice(int benefitPrice) {
        if (benefitPrice < 0) {
            throw new IllegalStateException("[ERROR] 예기치 못한 예외 - benefitPrice < 0");
        }
    }
}
