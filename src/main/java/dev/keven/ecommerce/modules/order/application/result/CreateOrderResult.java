package dev.keven.ecommerce.modules.order.application.result;

import java.math.BigDecimal;

public record CreateOrderResult(
        Long orderId,
        BigDecimal totalPrice,
        String status
) {
}
