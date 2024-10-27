package com.example.demo.domain.repository;

import com.example.demo.domain.model.Size;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

/**
 * @author Gerardo De Las Cuevas
 */
public interface SizeRepository {
    Optional<Size> findById(Long id);
    Optional<Size> findBySizeName(String sizeName);
    List<Size> findAll();
    Size save(Size size);
    boolean existByName(String sizeName);
    boolean existById(Long id);
    void deleteById(Long id);
}
