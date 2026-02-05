package dev.keven.ecommerce.modules.order.application.usecase;

import dev.keven.ecommerce.common.exception.OrderNotFoundException;
import dev.keven.ecommerce.modules.order.application.command.DeleteOrderCommand;
import dev.keven.ecommerce.modules.order.application.gateway.OrderGateway;
import dev.keven.ecommerce.modules.order.domain.Order;

public class DeleteOrderUseCase {

    private final OrderGateway orderGateway;

    public DeleteOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public void execute(DeleteOrderCommand command) {
        Order order = orderGateway.findById(command.orderId())
                .orElseThrow(() -> new OrderNotFoundException("order not found"));

        order.validateIfCanBeDeleted();

        orderGateway.deleteById(order.getId());
    }
}
