package christmas.domain.order.food;

import christmas.global.exception.OrderExceptionMessage;

import java.util.*;

import static christmas.domain.order.food.FoodCategory.*;
import static christmas.global.exception.OrderExceptionMessage.INVALID_FOOD_INPUT;

public enum Food {
    양송이수프(APPETIZER,"양송이수프", 6000),
    타파스(APPETIZER, "타파스", 5500),
    시저샐러드(APPETIZER, "시저샐러드", 8000),

    티본스테이크(MAIN_DISH, "티본스테이크", 55000),
    바비큐립(MAIN_DISH, "바비큐립", 54000),
    해산물파스타(MAIN_DISH, "해산물파스타",35000),
    크리스마스파스타(MAIN_DISH, "크리스마스파스타",25000),

    초코케이크(DESSERT, "초코케이크",15000),
    아이스크림(DESSERT, "아이스크림",5000),

    제로콜라(DRINK, "제로콜라",3000),
    레드와인(DRINK, "레드와인",60000),
    샴페인(DRINK, "샴페인",25000);

    private final FoodCategory foodCategory;
    private final String food;
    private final int price;

    Food(FoodCategory foodCategory, String food, int price) {
        this.foodCategory = foodCategory;
        this.food = food;
        this.price = price;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public String getFood() {
        return food;
    }

    public int getPrice() {
        return price;
    }

    public static Food findByFood(String food) {
        return Arrays.stream(Food.values())
                .filter(f -> f.food.equals(food))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FOOD_INPUT.get()));
    }

    public static Map<FoodCategory, Integer> sortByFoodCategory(String orders) {
        Map<FoodCategory, Integer> foodCategoryMap = new EnumMap<>(FoodCategory.class);

        Map<Food, Integer> foodMap = toFoodMap(orders);
        for (Food food : foodMap.keySet()) {
            FoodCategory foodCategory = food.foodCategory;
            int count = foodCategoryMap.getOrDefault(foodCategory, 0);
            count += foodMap.get(food);

            foodCategoryMap.put(foodCategory, count);
        }

        return foodCategoryMap;
    }

    public static int calculateTotalPrice(String orders) {
        Map<Food, Integer> foodMap = toFoodMap(orders);
        int totalPrice = 0;

        for (Food food : foodMap.keySet()) {
            int price = food.price;
            int foodCount = foodMap.get(food);
            totalPrice += price * foodCount;
        }

        return totalPrice;
    }

    public static String generateOrderedMenu(String orders) {
        StringBuilder menu = new StringBuilder();
        Map<Food, Integer> foodMap = toFoodMap(orders);
        for (Food food : foodMap.keySet()) {
            String foodName = food.food;
            int count = foodMap.get(food);
            String order = String.format("%s %s개\n",foodName, count);
            menu.append(order);
        }
        return menu.toString();

    }


    public static Map<Food, Integer> toFoodMap(String orders) {
        Map<Food, Integer> foodMap = new EnumMap<>(Food.class);
        List<String> orderList = Arrays.stream(orders.split(","))
                .toList();

        orderList.stream()
                .map(String::trim)
                .forEach(o -> {
                    sortByFoods(foodMap, o);
                });

        return foodMap;
    }

    private static void sortByFoods(Map<Food, Integer> foodMap, String o) {
        String[] order = o.split("-");
        Food food = findByFood(order[0]);
        int foodCount = Integer.parseInt(order[1]);

        int count = foodMap.getOrDefault(food, 0);
        validateDuplicateMenu(count);
        count += foodCount;
        foodMap.put(food, count);
    }

    private static void validateDuplicateMenu(int count) {
        if(count != 0) {
            throw new IllegalArgumentException(INVALID_FOOD_INPUT.get());
        }
    }
}
