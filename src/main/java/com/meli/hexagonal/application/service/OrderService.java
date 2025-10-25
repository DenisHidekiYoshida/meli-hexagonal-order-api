package com.meli.hexagonal.application.service;

import com.meli.hexagonal.application.port.in.CreateOrderUseCase;
import com.meli.hexagonal.application.port.out.LoadOrderPort;
import com.meli.hexagonal.application.port.out.SaveOrderPort;
import com.meli.hexagonal.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateOrderUseCase {

    private final SaveOrderPort saveOrderPort;
    private final LoadOrderPort loadOrderPort;

    @Override
    public Order create(String customerName) {
        Order order = new Order(customerName, "NOVO");
        return saveOrderPort.save(order);
    }

    public Order findById(Long id) {
        return loadOrderPort.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void cancel(Long id) {
        Order order = loadOrderPort.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        order.cancel();
        saveOrderPort.save(order);
    }
}
