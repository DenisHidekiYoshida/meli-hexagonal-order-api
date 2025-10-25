package com.meli.hexagonal.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String status;

    private LocalDateTime createdAt;

    public Order() {}

    public Order(String customerName, String status) {
        this.customerName = customerName;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void cancel() {
        if ("CANCELADO".equals(this.status)) {
            throw new IllegalStateException("Pedido já cancelado");
        }
        this.status = "CANCELADO";
    }
}
