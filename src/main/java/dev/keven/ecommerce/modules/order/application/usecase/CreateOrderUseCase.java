package dev.keven.ecommerce.modules.order.application.usecase;

import dev.keven.ecommerce.modules.order.application.command.CreateOrderCommand;
import dev.keven.ecommerce.modules.order.application.gateway.OrderGateway;
import dev.keven.ecommerce.modules.order.application.result.CreateOrderResult;
import dev.keven.ecommerce.modules.order.domain.Order;
import dev.keven.ecommerce.modules.order.presentation.dto.request.CreateOrderRequest;
import dev.keven.ecommerce.modules.order.presentation.dto.response.CreateOrderResponse;

public class CreateOrderUseCase {

    private final OrderGateway orderGateway;

    public CreateOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public CreateOrderResult execute(CreateOrderCommand command) {
        Order order = new Order(command.userId());

        command.items().forEach(item -> {
            order.addItem(item.productId(), item.quantity(), item.price());
        });

        Order saved = orderGateway.save(order);

        return new CreateOrderResult(
                saved.getId(),
                saved.getTotalPrice(),
                saved.getStatus().name()
        );
    }
}
