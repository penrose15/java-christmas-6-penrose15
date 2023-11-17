package christmas.domain.order.enums;

public enum OrderNumberDefinition {
    MAX_ORDER_QUANTITY(20);

    private final int number;

    OrderNumberDefinition(int number) {
        this.number = number;
    }

    public int get() {
        return number;
    }
}
