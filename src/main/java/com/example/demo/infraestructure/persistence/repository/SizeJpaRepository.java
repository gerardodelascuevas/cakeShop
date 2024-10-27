package com.example.demo.infraestructure.persistence.repository;

import com.example.demo.domain.model.Flavor;
import com.example.demo.domain.model.Size;
import com.example.demo.domain.repository.SizeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.findByName;

/**
 * @author Gerardo De Las Cuevas
 */
public interface SizeJpaRepository extends JpaRepository<Size, Long>, SizeRepository {
    @Override
    default boolean existByName(String name) {
        return findBySizeName(name).isPresent();
    }

    @Override
    default boolean existById(Long id){
        return existsById(id);
    }
}
