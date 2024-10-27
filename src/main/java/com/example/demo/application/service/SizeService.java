package com.example.demo.application.service;

import com.example.demo.domain.model.Flavor;
import com.example.demo.domain.model.Size;
import com.example.demo.domain.repository.SizeRepository;
import com.example.demo.domain.service.SizeDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Gerardo De Las Cuevas
 */
@Service
public class SizeService implements SizeDomainService {
    private final SizeRepository repository;

    public SizeService(SizeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Size> getAll() {
        return repository.findAll();
    }

    @Override
    public Size update(Long id, Size size) {
        Optional<Size> clientSize = repository.findById(id);
        Size clientSizeBool = clientSize.orElseThrow(() -> new IllegalArgumentException("Size not found"));

        clientSizeBool.setSizeName(size.getSizeName());
        clientSizeBool.setAvailable(size.isAvailable());
        return repository.save(clientSizeBool);
    }

    @Override
    public Size store(Size size) {
        if(size.getSizeName() == null){
            throw new IllegalArgumentException("Size name is required");
        }
        if(repository.existByName(size.getSizeName())){
            throw new IllegalArgumentException("This size is already register");
        }
        if(size.getSizeName().length() > 255){
            throw  new IllegalArgumentException("Size name cannot exceed 255 characters");
        }
        try {
            return repository.save(size);
        } catch(Exception e){
            throw new RuntimeException("Error saving Size: " + e.getMessage());
        }

    }

    @Override
    public String destroy(Long id) {
        if (!repository.existById(id)) {
            throw new IllegalArgumentException("Size not found with id: " + id);
        }
        try {
            repository.deleteById(id);
            return "Size removed completely";
        } catch (Exception e) {
            throw new RuntimeException("Error removing Size: " + e.getMessage());
        }
    }

}
