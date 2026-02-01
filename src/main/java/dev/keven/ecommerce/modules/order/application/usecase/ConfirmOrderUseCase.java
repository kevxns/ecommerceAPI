package dev.keven.ecommerce.modules.order.application.usecase;

import dev.keven.ecommerce.modules.order.application.gateway.OrderGateway;
import dev.keven.ecommerce.modules.order.domain.Order;
import dev.keven.ecommerce.modules.order.presentation.dto.request.ConfirmOrderRequest;
import dev.keven.ecommerce.modules.order.presentation.dto.response.ConfirmOrderResponse;

public class ConfirmOrderUseCase {

    private final OrderGateway orderGateway;

    public ConfirmOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public ConfirmOrderResponse execute(ConfirmOrderRequest request) {
        Order order = orderGateway.findById(request.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.confirm();

        Order updated = orderGateway.save(order);

        return new ConfirmOrderResponse(
                updated.getId(),
                updated.getStatus().name(),
                updated.getTotalPrice()
        );
    }
}
