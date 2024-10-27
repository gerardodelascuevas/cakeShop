package com.example.demo.infraestructure.persistence.repository;

import com.example.demo.domain.model.Order;
import com.example.demo.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gerardo De Las Cuevas
 */
@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long>, OrderRepository {

}
