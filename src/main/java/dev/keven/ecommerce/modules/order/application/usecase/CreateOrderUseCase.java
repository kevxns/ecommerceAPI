package dev.keven.ecommerce.modules.order.application.usecase;

import dev.keven.ecommerce.modules.order.application.gateway.OrderGateway;
import dev.keven.ecommerce.modules.order.domain.Order;
import dev.keven.ecommerce.modules.order.presentation.dto.request.CreateOrderRequest;
import dev.keven.ecommerce.modules.order.presentation.dto.response.CreateOrderResponse;

public class CreateOrderUseCase {

    private final OrderGateway orderGateway;

    public CreateOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public CreateOrderResponse execute(CreateOrderRequest request) {
        Order order = new Order(request.userId());

        Order saved = orderGateway.save(order);

        return new CreateOrderResponse(
                saved.getId(),
                saved.getTotalPrice(),
                saved.getStatus().name()
        );
    }
}
