package com.meli.hexagonal.application.service;

import com.meli.hexagonal.application.port.out.LoadOrderPort;
import com.meli.hexagonal.application.port.out.SaveOrderPort;
import com.meli.hexagonal.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private final SaveOrderPort saveOrderPort = mock(SaveOrderPort.class);
    private final LoadOrderPort loadOrderPort = mock(LoadOrderPort.class);
    private final OrderService service = new OrderService(saveOrderPort, loadOrderPort);

    @Test
    void shouldCreateOrder() {
        when(saveOrderPort.save(any(Order.class))).thenAnswer(inv -> inv.getArgument(0));
        Order created = service.create("João");
        assertNotNull(created);
        assertEquals("NOVO", created.getStatus());
        verify(saveOrderPort, times(1)).save(any(Order.class));
    }

    @Test
    void shouldCancelOrder() {
        Order order = new Order("João", "NOVO");
        when(loadOrderPort.findById(1L)).thenReturn(Optional.of(order));
        service.cancel(1L);
        assertEquals("CANCELADO", order.getStatus());
        verify(saveOrderPort, times(1)).save(order);
    }
}
