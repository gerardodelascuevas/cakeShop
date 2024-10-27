package com.example.demo.domain.repository;

import com.example.demo.domain.model.Flavor;
import java.util.List;
import java.util.Optional;
/**
 * @author Gerardo De Las Cuevas
 */
public interface FlavorRepository {
    List<Flavor> findAll();
    Flavor save(Flavor flavor);
    Optional<Flavor> findById(Long id);
    List<Flavor> findByNameIn(List<String> names);
    Boolean existsByName(String name);
    void deleteById(Long id);
    boolean existsById(Long id);
}