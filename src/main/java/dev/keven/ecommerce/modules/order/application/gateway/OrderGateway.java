package dev.keven.ecommerce.modules.order.application.gateway;

import dev.keven.ecommerce.modules.order.domain.Order;
import java.util.Optional;

public interface OrderGateway {
    Order save(Order order);
    Optional<Order> findById(Long id);
    void deleteById(Long id);
}
