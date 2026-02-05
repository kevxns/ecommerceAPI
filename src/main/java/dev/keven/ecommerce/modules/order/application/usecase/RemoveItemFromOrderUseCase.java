package dev.keven.ecommerce.modules.order.application.usecase;

import dev.keven.ecommerce.common.exception.OrderNotFoundException;
import dev.keven.ecommerce.common.exception.ProductNotFoundException;
import dev.keven.ecommerce.modules.order.application.command.RemoveItemFromOrderCommand;
import dev.keven.ecommerce.modules.order.application.gateway.OrderGateway;
import dev.keven.ecommerce.modules.order.application.result.RemoveItemFromOrderResult;
import dev.keven.ecommerce.modules.order.domain.Order;
import dev.keven.ecommerce.modules.product.application.gateway.ProductGateway;
import dev.keven.ecommerce.modules.product.domain.Product;

public class RemoveItemFromOrderUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;

    public RemoveItemFromOrderUseCase(OrderGateway orderGateway, ProductGateway productGateway) {
        this.orderGateway = orderGateway;
        this.productGateway = productGateway;
    }

    public RemoveItemFromOrderResult execute(RemoveItemFromOrderCommand command) {
        Order order = orderGateway.findById(command.orderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        if (!productGateway.existsById(command.productId())) {
            throw new ProductNotFoundException("product not found");
        }

        order.removeItem(command.productId());

        Order updated = orderGateway.save(order);

        return new RemoveItemFromOrderResult(
                updated.getId(),
                updated.getItems().size(),
                updated.getTotalPrice().toString()
        );
    }
}
