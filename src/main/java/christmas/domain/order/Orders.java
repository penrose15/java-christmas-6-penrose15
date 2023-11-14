package christmas.domain.order;

import christmas.domain.order.food.Food;
import christmas.domain.order.food.FoodCategory;

import java.util.Map;

import static christmas.global.exception.OrderExceptionMessage.INVALID_FOOD_INPUT;

public class Orders {
    private static final String SEPARATOR = ",";
    private static final String HYPHEN = "-";

    private String orders;

    public void takeOrders(String orders) {
        validateOrdersNotStartOrEndWithComma(orders);
        validateOrdersSeparatedWithHyphen(orders);
        this.orders = orders;
    }

    private void validateOrdersNotStartOrEndWithComma(String orders) {
        if (orders.startsWith(SEPARATOR)) {
            throw new IllegalArgumentException(INVALID_FOOD_INPUT.get());
        }
        if (orders.endsWith(SEPARATOR)) {
            throw new IllegalArgumentException(INVALID_FOOD_INPUT.get());
        }
    }

    private void validateOrdersSeparatedWithHyphen(String inputOrder) {
        String[] orders = inputOrder.split(SEPARATOR);
        for (String order : orders) {
            try {
                String[] orderInfo = order.split(HYPHEN);
                validateOrderCountIsNumber(orderInfo);
            } catch (Exception e) {
                throw new IllegalArgumentException(INVALID_FOOD_INPUT.get());
            }
        }
    }

    private void validateOrderCountIsNumber(String[] order) {
        try {
            Integer.parseInt(order[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FOOD_INPUT.get());
        }
    }

    public Map<FoodCategory, Integer> sortByFoodCategory() {
        return Food.sortByFoodCategory(orders);
    }

    public String generateOrderedMenu() {
        return Food.generateOrderedMenu(orders);

    }

    public int calculateTotalPrice() {
        return Food.calculateTotalPrice(orders);
    }
}
