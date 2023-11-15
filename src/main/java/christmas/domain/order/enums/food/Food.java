package christmas.domain.order.enums.food;

import java.util.*;

import static christmas.global.exception.OrderExceptionMessage.INVALID_ORDERS;

public enum Food {
    양송이수프(FoodCategory.APPETIZER,"양송이수프", 6000),
    타파스(FoodCategory.APPETIZER, "타파스", 5500),
    시저샐러드(FoodCategory.APPETIZER, "시저샐러드", 8000),

    티본스테이크(FoodCategory.MAIN_DISH, "티본스테이크", 55000),
    바비큐립(FoodCategory.MAIN_DISH, "바비큐립", 54000),
    해산물파스타(FoodCategory.MAIN_DISH, "해산물파스타",35000),
    크리스마스파스타(FoodCategory.MAIN_DISH, "크리스마스파스타",25000),

    초코케이크(FoodCategory.DESSERT, "초코케이크",15000),
    아이스크림(FoodCategory.DESSERT, "아이스크림",5000),

    제로콜라(FoodCategory.DRINK, "제로콜라",3000),
    레드와인(FoodCategory.DRINK, "레드와인",60000),
    샴페인(FoodCategory.DRINK, "샴페인",25000);

    private final FoodCategory foodCategory;
    private final String food;
    private final int price;

    Food(FoodCategory foodCategory, String food, int price) {
        this.foodCategory = foodCategory;
        this.food = food;
        this.price = price;
    }


    public static Food findByFood(String food) {
        return Arrays.stream(Food.values())
                .filter(f -> f.food.equals(food))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDERS.get()));
    }

    public static Map<FoodCategory, Integer> sortByFoodCategory(Map<Food, Integer> foodMap) {
        Map<FoodCategory, Integer> foodCategoryMap = new EnumMap<>(FoodCategory.class);

        for (Food food : foodMap.keySet()) {
            FoodCategory foodCategory = food.foodCategory;
            int count = foodCategoryMap.getOrDefault(foodCategory, 0);
            count += foodMap.get(food);

            foodCategoryMap.put(foodCategory, count);
        }

        return foodCategoryMap;
    }

    public static int calculateTotalPrice(Map<Food, Integer> foodMap) {
        int totalPrice = 0;

        for (Food food : foodMap.keySet()) {
            int price = food.price;
            int foodCount = foodMap.get(food);
            totalPrice += price * foodCount;
        }

        return totalPrice;
    }

    public static String generateOrderedMenu(Map<Food, Integer> foodMap) {
        StringBuilder menu = new StringBuilder();

        for (Food food : foodMap.keySet()) {
            String foodName = food.food;
            int count = foodMap.get(food);
            String order = String.format("%s %s개\n",foodName, count);
            menu.append(order);
        }
        return menu.toString();

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
}
