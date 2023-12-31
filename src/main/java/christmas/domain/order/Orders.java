package christmas.domain.order;

import christmas.domain.order.enums.food.Food;
import christmas.domain.order.enums.food.FoodCategory;

import java.util.*;

import static christmas.global.exception.OrderExceptionMessage.INVALID_ORDERS;
import static christmas.domain.order.enums.OrderNumberDefinition.MAX_ORDER_QUANTITY;

public class Orders {
    private static final String SEPARATOR = ",";
    private static final String HYPHEN = "-";

    private Map<Food, Integer> orderMap;

    private static final Orders instance = new Orders();

    private Orders() {}

    public static Orders getInstance() {
        return instance;
    }


    public void takeOrders(String orders) {
        orderMap = separateByFood(orders);
    }

    private Map<Food, Integer> separateByFood(String orders) {
        Map<Food, Integer> foodMap = new EnumMap<>(Food.class);
        List<String> orderList = Arrays.stream(orders.split(SEPARATOR))
                .toList();

        orderList.stream()
                .map(String::trim)
                .forEach(o -> sortByFoods(foodMap, o));
        validateOrdersQuantityIsInRange(foodMap);
        validateOrdersConsistOfDrinkOnly(foodMap);
        return foodMap;
    }



    public void sortByFoods(Map<Food, Integer> foodMap, String inputOrder) {
        String[] order = inputOrder.split(HYPHEN);
        Food food = Food.findByFood(order[0]);
        int foodCount = Integer.parseInt(order[1]);

        validateOrderQuantityIsPositive(foodCount);
        int count = foodMap.getOrDefault(food, 0);
        validateDuplicateMenu(count);
        count += foodCount;
        foodMap.put(food, count);
    }

    private void validateOrderQuantityIsPositive(int count) {
        if(count < 1) {
            throw new IllegalArgumentException(INVALID_ORDERS.get());
        }
    }


    private void validateDuplicateMenu(int count) {
        if(count != 0) {
            throw new IllegalArgumentException(INVALID_ORDERS.get());
        }
    }

    private void validateOrdersQuantityIsInRange(Map<Food, Integer> foodMap) {
        int totalQuantity = 0;
        for (Integer value : foodMap.values()) {
            totalQuantity += value;
        }
        if(totalQuantity > MAX_ORDER_QUANTITY.get()) {
            throw new IllegalArgumentException(INVALID_ORDERS.get());
        }
    }

    private void validateOrdersConsistOfDrinkOnly(Map<Food, Integer> foodMap) {
        int notDrinkCount = 0;
        for (Food food : foodMap.keySet()) {
            notDrinkCount = countNotDrinkCategory(notDrinkCount, food);
        }
        if(notDrinkCount == 0) {
            throw new IllegalArgumentException(INVALID_ORDERS.get());
        }
    }

    private int countNotDrinkCategory(int notDrinkCount, Food food) {
        if(!food.getFoodCategory().equals(FoodCategory.DRINK)) {
            notDrinkCount += 1;
        }
        return notDrinkCount;
    }

    public Map<FoodCategory, Integer> sortByFoodCategory() {
        return Food.sortByFoodCategory(orderMap);
    }

    public String generateOrderedMenu() {
        return Food.generateOrderedMenu(orderMap);

    }

    public int calculateTotalPrice() {
        return Food.calculateTotalPrice(orderMap);
    }


    public Map<Food, Integer> getOrderMap() {
        return orderMap;
    }
}
