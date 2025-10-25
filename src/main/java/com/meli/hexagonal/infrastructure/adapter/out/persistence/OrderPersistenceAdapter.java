package com.meli.hexagonal.infrastructure.adapter.out.persistence;

import com.meli.hexagonal.application.port.out.LoadOrderPort;
import com.meli.hexagonal.application.port.out.SaveOrderPort;
import com.meli.hexagonal.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements SaveOrderPort, LoadOrderPort {

    private final JpaOrderRepository repository;

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }
}
