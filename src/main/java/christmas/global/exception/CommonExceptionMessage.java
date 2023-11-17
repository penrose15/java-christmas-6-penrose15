package christmas.global.exception;

public enum CommonExceptionMessage implements ExceptionMessage{

    EXCEPTION_PREFIX("[ERROR] "),
    UNEXPECTED_EXCEPTION("예기치 못한 에러");

    private final String message;

    CommonExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String get() {
        return message;
    }
}
