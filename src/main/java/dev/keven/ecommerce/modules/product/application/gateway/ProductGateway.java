package dev.keven.ecommerce.modules.product.application.gateway;

import dev.keven.ecommerce.modules.product.domain.Product;
import java.util.Optional;

public interface ProductGateway {
    Product save(Product product);
    Product update(Product product);
    Optional<Product> findById(Long id);
    void deleteById(Long id);
    boolean existsByName(String name);
}
