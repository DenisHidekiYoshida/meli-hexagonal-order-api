package com.meli.hexagonal.infrastructure.adapter.in.web;

import com.meli.hexagonal.application.port.in.CreateOrderUseCase;
import com.meli.hexagonal.application.service.OrderService;
import com.meli.hexagonal.domain.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Gerenciamento de pedidos")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final OrderService orderService;

    @Operation(summary = "Cria um novo pedido")
    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order request) {
        Order created = createOrderUseCase.create(request.getCustomerName());
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Busca pedido por id")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @Operation(summary = "Cancelar pedido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        orderService.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
