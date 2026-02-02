package dev.keven.ecommerce.modules.order.infrastructure.persistence.repository;

import dev.keven.ecommerce.modules.order.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
