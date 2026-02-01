package dev.keven.ecommerce.modules.order.application.usecase;

import dev.keven.ecommerce.modules.order.application.gateway.OrderGateway;
import dev.keven.ecommerce.modules.order.domain.Order;
import dev.keven.ecommerce.modules.order.presentation.dto.request.CancelOrderRequest;
import dev.keven.ecommerce.modules.order.presentation.dto.response.CancelOrderResponse;

public class CancelOrderUseCase {

    private final OrderGateway orderGateway;

    public CancelOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public CancelOrderResponse execute(CancelOrderRequest request) {
        Order order = orderGateway.findById(request.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.cancel();

        Order updated = orderGateway.save(order);

        return new CancelOrderResponse(
                updated.getId(),
                updated.getStatus().name()
        );
    }
}
