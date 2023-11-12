package christmas.global.view.message;

public enum OrderDynamicMessage {

    SHOW_EVENT("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
    ;

    private final String message;


    OrderDynamicMessage(String message) {
        this.message = message;
    }

    public String get(int number) {
        return String.format(this.message, number);
    }

    public String get() {
        return message;
    }
}
