package com.meli.hexagonal.application.port.out;

import com.meli.hexagonal.domain.model.Order;
import java.util.Optional;

public interface LoadOrderPort {
    Optional<Order> findById(Long id);
}
