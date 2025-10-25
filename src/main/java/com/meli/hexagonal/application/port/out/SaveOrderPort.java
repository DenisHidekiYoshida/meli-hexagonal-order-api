package com.meli.hexagonal.application.port.out;

import com.meli.hexagonal.domain.model.*;

public interface SaveOrderPort {
    Order save(Order order);
}
