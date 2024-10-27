package com.example.demo.infraestructure.persistence.repository;

import com.example.demo.domain.model.Flavor;
import com.example.demo.domain.repository.FlavorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Gerardo De Las Cuevas
 */
@Repository
public interface FlavorJpaRepository extends JpaRepository<Flavor, Long>, FlavorRepository {
    List<Flavor> findByNameIn(List<String> names);

    @Override
    default Boolean existsByName(String name) {
        return findByName(name).isPresent();
    }
    Optional<Flavor> findByName(String name);
}

