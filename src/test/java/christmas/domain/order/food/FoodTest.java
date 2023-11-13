package christmas.domain.order.food;

import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static christmas.domain.order.food.Food.*;
import static christmas.global.exception.OrderExceptionMessage.INVALID_FOOD_INPUT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    List<String> foodInMenus = List.of(
            "양송이수프", "타파스", "시저샐러드",
            "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타",
            "초코케이크", "아이스크림",
            "제로콜라", "레드와인", "샴페인"
    );

    List<Food> foods = List.of(양송이수프, 타파스, 시저샐러드, 티본스테이크, 바비큐립, 해산물파스타, 크리스마스파스타, 초코케이크, 아이스크림, 제로콜라, 레드와인, 샴페인);

    @Test
    public void 메뉴에_포함된_음식_입력시_음식을_찾는지_확인() {
        for (int i = 0; i < foodInMenus.size(); i++) {
            String foodInMenu = foodInMenus.get(i);
            Food food = foods.get(i);

            Food result = Food.findByFood(foodInMenu);

            assertThat(food)
                    .isEqualTo(result);
        }
    }

    @Test
    public void 주문이_들어오면_카테고리별로_분류가_되는지_테스트() {
        String orders = "해산물파스타-2,레드와인-1,초코케이크-1";

        Map<FoodCategory, Integer> foodCategoryMap = new EnumMap<>(FoodCategory.class);
        foodCategoryMap.put(FoodCategory.MAIN_DISH, 2);
        foodCategoryMap.put(FoodCategory.DRINK, 1);
        foodCategoryMap.put(FoodCategory.DESSERT, 1);

        Map<FoodCategory, Integer> result = Food.sortByFoodCategory(orders);

        assertThat(result)
                .isEqualTo(foodCategoryMap);
    }

    @Test
    public void 주문_중복시_예외처리_테스트() {
        String orders = "초코케이크-2,레드와인-1,초코케이크-1";

        assertThatThrownBy(() -> Food.sortByFoodCategory(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_FOOD_INPUT.get());
    }

    @Test
    public void 가격_계산() {
        String orders = "해산물파스타-2,레드와인-1,초코케이크-1";

        int expected = 145_000;

        int result = Food.calculateTotalPrice(orders);

        assertThat(result)
                .isEqualTo(expected);
    }

    @Test
    public void 주문내역_생성_테스트() {
        String orders = "타파스-1,제로콜라-1";

        String expected
                = """
                타파스 1개
                제로콜라 1개
                """;

        String result = Food.generateOrderedMenu(orders);

        assertThat(result)
                .isEqualTo(expected);
    }

}