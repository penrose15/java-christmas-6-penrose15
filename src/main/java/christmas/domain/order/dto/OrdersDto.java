package christmas.domain.order.dto;

import static christmas.global.exception.OrderExceptionMessage.INVALID_ORDERS;

public record OrdersDto(String orders) {
    private static final String SEPARATOR = ",";
    private static final String HYPHEN = "-";

    public OrdersDto {
        validateOrdersNotStartOrEndWithComma(orders);
        validateOrdersSeparatedWithHyphen(orders);
    }

    private void validateOrdersNotStartOrEndWithComma(String orders) {
        if (orders.startsWith(SEPARATOR)) {
            throw new IllegalArgumentException(INVALID_ORDERS.get());
        }
        if (orders.endsWith(SEPARATOR)) {
            throw new IllegalArgumentException(INVALID_ORDERS.get());
        }
    }

    private void validateOrdersSeparatedWithHyphen(String inputOrder) {
        String[] orders = inputOrder.split(SEPARATOR);
        for (String order : orders) {
            try {
                String[] orderInfo = order.split(HYPHEN);
                validateOrderCountIsNumber(orderInfo);
            } catch (Exception e) {
                throw new IllegalArgumentException(INVALID_ORDERS.get());
            }
        }
    }

    private void validateOrderCountIsNumber(String[] order) {
        try {
            Integer.parseInt(order[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDERS.get());
        }
    }
}
