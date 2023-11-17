package christmas.domain.sale.enums;

public enum Giveaway {
    CHAMPAGNE("샴페인 1개",120_000, 25_000),
    NONE("없음",0, 0);

    private final String product;
    private final int criteriaAmount;
    private final int productPrice;

    Giveaway(String product, int criteriaAmount, int productPrice) {
        this.product = product;
        this.criteriaAmount = criteriaAmount;
        this.productPrice = productPrice;
    }

    public static Giveaway isGiveawayTarget(int totalPrice) {
        if(totalPrice >= 120_000) {
            return CHAMPAGNE;
        }
        return NONE;
    }

    public String getProduct() {
        return product;
    }

    public int getCriteriaAmount() {
        return criteriaAmount;
    }

    public int getProductPrice() {
        return productPrice;
    }
}
