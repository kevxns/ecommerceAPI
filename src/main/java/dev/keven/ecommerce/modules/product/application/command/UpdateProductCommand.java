package dev.keven.ecommerce.modules.product.application.command;

import dev.keven.ecommerce.modules.product.domain.ProductStatus;
import java.math.BigDecimal;

public record UpdateProductCommand(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        ProductStatus status
) {
}
