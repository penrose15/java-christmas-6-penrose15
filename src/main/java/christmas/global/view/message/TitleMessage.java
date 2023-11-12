package christmas.global.view.message;

public enum TitleMessage {
    ORDER_MENU("<주문 메뉴>"),
    BEFORE_SALE_PRICE("<할인 전 총주문 금액>"),
    GIVEAWAY_MENU("<증정 메뉴>"),
    BENEFIT("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    AFTER_SALE_PRICE("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>");

    private final String title;

    TitleMessage(String title) {
        this.title = title;
    }

    public String get() {
        return title;
    }
}
