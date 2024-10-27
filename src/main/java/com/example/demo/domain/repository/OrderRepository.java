package com.example.demo.domain.repository;

import com.example.demo.domain.model.Order;

import java.util.List;
import java.util.Optional;

/**
 * @author Gerardo De Las Cuevas
 */
public interface OrderRepository {
    Order save(Order order);
    List<Order> findAll();
    Optional<Order> findById(Long orderId);
    List<Order> findByClientId(Long clientId);

    void deleteById(Long id);
}
