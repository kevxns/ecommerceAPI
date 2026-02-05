package dev.keven.ecommerce.modules.order.application.command;

public record RemoveItemFromOrderCommand(
        Long orderId,
        Long productId
) {
}
