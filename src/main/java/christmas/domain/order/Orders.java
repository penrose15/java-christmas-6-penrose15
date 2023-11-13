package christmas.domain.order;

import christmas.domain.order.food.Food;
import christmas.domain.order.food.FoodCategory;

import java.util.Map;

import static christmas.global.exception.OrderExceptionMessage.INVALID_FOOD_INPUT;

public class Orders {

    private String orders;

    public void takeOrders(String orders) {
        validateOrdersNotStartOrEndWithComma(orders);
        validateOrdersSeparatedWithHyphen(orders);
        this.orders = orders;
    }

    private void validateOrdersNotStartOrEndWithComma(String orders) {
        if(orders.startsWith(",")) {
            throw new IllegalArgumentException(INVALID_FOOD_INPUT.get());
        }
        if(orders.endsWith(",")) {
            throw new IllegalArgumentException(INVALID_FOOD_INPUT.get());
        }
    }

    private void validateOrdersSeparatedWithHyphen(String inputOrder) {
        String[] orders = inputOrder.split(",");
        for (String order : orders) {
            try {
                String[] orderInfo = order.split("-");
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
