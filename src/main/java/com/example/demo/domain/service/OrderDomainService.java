package com.example.demo.domain.service;

import com.example.demo.domain.model.Order;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Gerardo De Las Cuevas
 */
public interface OrderDomainService {
    Order store(Order order);
    List<Order> getAllOrders();
    List<Order> getByClientId(Long id);
    List<Order> getByDate(LocalDateTime startDate, LocalDateTime endDate);
}
