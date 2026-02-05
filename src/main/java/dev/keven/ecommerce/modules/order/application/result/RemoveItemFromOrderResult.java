package dev.keven.ecommerce.modules.order.application.result;

public record RemoveItemFromOrderResult(
        Long orderId,
        int totalItems,
        String totalPrice
) {
}
