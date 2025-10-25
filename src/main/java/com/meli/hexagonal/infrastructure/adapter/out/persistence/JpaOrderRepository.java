package com.meli.hexagonal.infrastructure.adapter.out.persistence;

import com.meli.hexagonal.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {
}
