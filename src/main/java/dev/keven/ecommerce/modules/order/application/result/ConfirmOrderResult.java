package dev.keven.ecommerce.modules.order.application.result;

public record ConfirmOrderResult(
        Long orderId,
        String status,
        int totalItems,
        String totalPrice
) {
}
