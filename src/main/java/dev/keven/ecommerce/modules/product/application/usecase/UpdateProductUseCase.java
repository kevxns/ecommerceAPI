package dev.keven.ecommerce.modules.product.application.usecase;

import dev.keven.ecommerce.common.exception.ProductAlreadyExistsException;
import dev.keven.ecommerce.common.exception.ProductNotFoundException;
import dev.keven.ecommerce.modules.product.application.gateway.ProductGateway;
import dev.keven.ecommerce.modules.product.domain.Product;
import dev.keven.ecommerce.modules.product.presentation.dto.request.UpdateProductRequest;
import dev.keven.ecommerce.modules.product.presentation.dto.response.UpdateProductResponse;
import java.time.LocalDateTime;

public class UpdateProductUseCase {

    private final ProductGateway productGateway;

    public UpdateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public UpdateProductResponse execute(Long id, UpdateProductRequest request) {

        if (productGateway.existsByName(request.name())) {
            throw new ProductAlreadyExistsException(String.format("Product with name '%s' already exists", request.name()));
        }

        var existent = productGateway.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        var updated = new Product(
                existent.getId(),
                request.name() != null ? request.name() : existent.getName(),
                request.description() != null ? request.description() : existent.getDescription(),
                request.price() != null ? request.price() : existent.getPrice(),
                request.stock() != null ? request.stock() : existent.getStock(),
                request.status() != null ? request.status() : existent.getStatus(),
                existent.getCreatedAt(),
                LocalDateTime.now()
        );

        productGateway.update(updated);

        return new UpdateProductResponse(
                updated.getName(),
                updated.getDescription(),
                updated.getPrice(),
                updated.getStock(),
                updated.getStatus(),
                updated.getUpdatedAt()
        );
    }
}
