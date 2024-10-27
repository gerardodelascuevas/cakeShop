package com.example.demo.domain.service;

import com.example.demo.domain.model.Size;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Gerardo De Las Cuevas
 */
@Service
public interface SizeDomainService {
    List<Size> getAll();

    Size update(Long id, Size size);

    Size store(Size size);

    String destroy(Long id);
}
