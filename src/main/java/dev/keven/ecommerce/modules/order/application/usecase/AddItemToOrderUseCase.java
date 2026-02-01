package dev.keven.ecommerce.modules.order.application.usecase;

import dev.keven.ecommerce.modules.order.application.gateway.OrderGateway;
import dev.keven.ecommerce.modules.order.domain.Order;
import dev.keven.ecommerce.modules.order.presentation.dto.request.AddItemRequest;
import dev.keven.ecommerce.modules.order.presentation.dto.response.AddItemResponse;

public class AddItemToOrderUseCase {

    private final OrderGateway orderGateway;

    public AddItemToOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public AddItemResponse execute(AddItemRequest request) {
        Order order = orderGateway.findById(request.orderId())
                .orElseThrow(() -> new IllegalArgumentException("orderId is required"));

        order.addItem(request.productId(), request.quantity(), request.price());

        Order updated = orderGateway.save(order);

        return new AddItemResponse(
                updated.getId(),
                updated.getTotalPrice(),
                updated.getItems().size()
        );
    }
}
