package dev.keven.ecommerce.modules.order.application.command;

public record AddItemToOrderCommand(
        Long orderId,
        Long productId,
        int quantity
) {
}
