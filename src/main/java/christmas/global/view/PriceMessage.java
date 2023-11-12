package christmas.global.view;

import java.text.NumberFormat;

public class PriceMessage {
    private static final String PRICE_FORMAT = "%s원";
    private static final String DISCOUNT_FORMAT = "-%s원";

    public static String getPriceMessage(int price) {
        String formattedPrice = getFormattedMoney(price);
        return String.format(PRICE_FORMAT, formattedPrice);
    }

    public static String getDiscountPriceMessage(int price) {
        String formattedPrice = getFormattedMoney(price);

        if(price > 0) {
            return String.format(DISCOUNT_FORMAT, formattedPrice);
        }
        return String.format(PRICE_FORMAT, formattedPrice);
    }

    private static String getFormattedMoney(int money) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        return numberFormat.format(money);
    }
}
