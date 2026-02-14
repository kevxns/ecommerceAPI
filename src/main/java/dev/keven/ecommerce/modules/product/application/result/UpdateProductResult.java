package dev.keven.ecommerce.modules.product.application.result;

import dev.keven.ecommerce.modules.product.domain.ProductStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateProductResult(
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        ProductStatus status,
        LocalDateTime updated
) {
}
