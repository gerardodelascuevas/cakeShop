package com.example.demo.application.service;

import com.example.demo.domain.model.Flavor;
import com.example.demo.domain.repository.FlavorRepository;
import com.example.demo.domain.service.FlavorDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Gerardo De Las Cuevas
 */
@Service
public class FlavorService implements FlavorDomainService {

    private final FlavorRepository repository;

    public FlavorService(FlavorRepository repository) {
        this.repository = repository;
    }

    public List<Flavor> getAll() {
        return repository.findAll();
    }

    @Override
    public Flavor create(Flavor flavor) {
        if (flavor.getName() == null || flavor.getName().isEmpty()) {
            throw new IllegalArgumentException("Flavor name cannot be null or empty");
        }

        if (repository.existsByName(flavor.getName())) {
            throw new IllegalArgumentException("Flavor with this name already exists");
        }

        if (flavor.getName().length() > 255) {
            throw new IllegalArgumentException("Flavor name cannot exceed 255 characters");
        }
        try {
            return repository.save(flavor);
        } catch (Exception e) {
            throw new RuntimeException("Error saving flavor: " + e.getMessage());
        }
    }

    @Override
    public String update(Long id, String name) {
        Optional<Flavor> optionalFlavor = repository.findById(id);
        Flavor flavor = optionalFlavor.orElseThrow(() -> new IllegalArgumentException("Flavor not found"));

        flavor.setName(name);
        return "Flavor updated";
    }

    @Override
    public String destroy(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Flavor not found with id: " + id);
        }
        try {
            repository.deleteById(id);
            return "Flavor removed completely";
        } catch (Exception e) {
            throw new RuntimeException("Error removing flavor: " + e.getMessage());
        }
    }
}
