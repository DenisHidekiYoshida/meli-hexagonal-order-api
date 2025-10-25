package com.meli.hexagonal.infrastructure.adapter.in.web;

import com.meli.hexagonal.application.port.in.CreateOrderUseCase;
import com.meli.hexagonal.application.service.OrderService;
import com.meli.hexagonal.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest {

    @Test
    void shouldCreateOrder() throws Exception {
        CreateOrderUseCase useCase = Mockito.mock(CreateOrderUseCase.class);
        OrderService orderService = Mockito.mock(OrderService.class);
        when(useCase.create(anyString())).thenReturn(new Order("João", "NOVO"));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(useCase, orderService)).build();

        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"customerName\":\"João\"}"))
                .andExpect(status().isOk());
    }
}
