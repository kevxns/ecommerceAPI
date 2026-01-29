package dev.keven.ecommerce.modules.product.application.usecase;

import dev.keven.ecommerce.common.exception.ProductNotFoundException;
import dev.keven.ecommerce.modules.product.application.gateway.ProductGateway;
import dev.keven.ecommerce.modules.product.domain.Product;
import java.util.Optional;

public class GetProductByIdUseCase {
    private final ProductGateway productGateway;

    public GetProductByIdUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Optional<Product> execute(Long id) {
        Optional<Product> product = productGateway.findById(id);
        if (product.isEmpty()) throw new ProductNotFoundException("Product not found");
        return product;
    }
}
