package dev.keven.ecommerce.modules.order.application.usecase;

import dev.keven.ecommerce.common.exception.OrderNotFoundException;
import dev.keven.ecommerce.modules.order.application.command.ConfirmOrderCommand;
import dev.keven.ecommerce.modules.order.application.gateway.OrderGateway;
import dev.keven.ecommerce.modules.order.application.result.ConfirmOrderResult;
import dev.keven.ecommerce.modules.order.domain.Order;

public class ConfirmOrderUseCase {

    private final OrderGateway orderGateway;

    public ConfirmOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public ConfirmOrderResult execute(ConfirmOrderCommand command) {
        Order order = orderGateway.findById(command.orderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.confirm();

        Order updated = orderGateway.save(order);

        return new ConfirmOrderResult(
                updated.getId(),
                updated.getStatus().name(),
                updated.getItems().size(),
                updated.getTotalPrice().toString()
        );
    }
}
