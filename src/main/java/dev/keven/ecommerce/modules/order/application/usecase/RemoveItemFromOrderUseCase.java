package dev.keven.ecommerce.modules.order.application.usecase;

import dev.keven.ecommerce.modules.order.application.gateway.OrderGateway;
import dev.keven.ecommerce.modules.order.domain.Order;
import dev.keven.ecommerce.modules.order.presentation.dto.request.RemoveItemRequest;
import dev.keven.ecommerce.modules.order.presentation.dto.response.RemoveItemResponse;

public class RemoveItemFromOrderUseCase {

    private final OrderGateway orderGateway;

    public RemoveItemFromOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public RemoveItemResponse execute(RemoveItemRequest request) {
        Order order = orderGateway.findById(request.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.removeItem(request.productId());

        Order updated = orderGateway.save(order);

        return new RemoveItemResponse(
                updated.getId(),
                updated.getTotalPrice(),
                updated.getItems().size()
        );
    }
}
