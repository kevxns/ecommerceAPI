package dev.keven.ecommerce.modules.product.application.result;

import dev.keven.ecommerce.modules.product.domain.Product;
import dev.keven.ecommerce.modules.product.domain.ProductStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetProductByIdResult(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        ProductStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static GetProductByIdResult from(Product product) {
        return new GetProductByIdResult(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
