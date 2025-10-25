package com.meli.hexagonal.application.port.in;

import com.meli.hexagonal.domain.model.Order;

public interface CreateOrderUseCase {
    Order create(String customerName);
}
