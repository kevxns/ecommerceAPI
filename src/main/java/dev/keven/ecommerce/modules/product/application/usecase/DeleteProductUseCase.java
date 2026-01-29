package dev.keven.ecommerce.modules.product.application.usecase;

import dev.keven.ecommerce.common.exception.ProductNotFoundException;
import dev.keven.ecommerce.modules.product.application.gateway.ProductGateway;
import dev.keven.ecommerce.modules.product.domain.Product;
import java.util.Optional;

public class DeleteProductUseCase {
    private final ProductGateway productGateway;

    public DeleteProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public void execute(Long productId) {
        Optional<Product> product = productGateway.findById(productId);
        if (product.isEmpty()) throw new ProductNotFoundException("Product not found");
        productGateway.deleteById(productId);
    }
}
