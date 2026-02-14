package dev.keven.ecommerce.modules.product.application.usecase;

import dev.keven.ecommerce.common.exception.ProductNotFoundException;
import dev.keven.ecommerce.modules.product.application.command.GetProductByIdCommand;
import dev.keven.ecommerce.modules.product.application.gateway.ProductGateway;
import dev.keven.ecommerce.modules.product.application.result.GetProductByIdResult;

public class GetProductByIdUseCase {
    private final ProductGateway productGateway;

    public GetProductByIdUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public GetProductByIdResult execute(GetProductByIdCommand command) {
        var product = productGateway.findById(command.productId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return GetProductByIdResult.from(product);
    }
}
