package christmas.global.exception;

public enum DateExceptionMessage implements ExceptionMessage{
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")

    ;

    private final String message;

    DateExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String get() {
        return this.message;
    }
}
