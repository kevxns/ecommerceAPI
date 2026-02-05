package dev.keven.ecommerce.modules.order.application.result;

public record CancelOrderResult(
        Long orderId,
        String status
) {
}
