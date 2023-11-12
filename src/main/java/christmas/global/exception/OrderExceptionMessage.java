package christmas.global.exception;

public enum OrderExceptionMessage implements ExceptionMessage{
    INVALID_FOOD_INPUT("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")

    ;

    private final String message;

    OrderExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String get() {
        return this.message;
    }
}
