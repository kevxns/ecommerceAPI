package dev.keven.ecommerce.modules.product.application.usecase;

import dev.keven.ecommerce.common.exception.ProductAlreadyExistsException;
import dev.keven.ecommerce.modules.product.application.gateway.ProductGateway;
import dev.keven.ecommerce.modules.product.domain.Product;
import dev.keven.ecommerce.modules.product.presentation.dto.request.CreateProductRequest;
import dev.keven.ecommerce.modules.product.presentation.dto.response.CreateProductResponse;
import java.time.LocalDateTime;

public class CreateProductUseCase {

    private final ProductGateway productGateway;

    public CreateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public CreateProductResponse execute(CreateProductRequest request) {
        if (productGateway.existsByName(request.name())) {
            throw new ProductAlreadyExistsException("Product already exists with this name");
        }

        Product product = new  Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setStatus(request.status());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        productGateway.save(product);

        return new CreateProductResponse(
                request.name(),
                request.description(),
                request.price(),
                request.stock(),
                request.status()
        );
    }
}
