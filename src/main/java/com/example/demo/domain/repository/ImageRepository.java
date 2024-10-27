package com.example.demo.domain.repository;

import com.example.demo.domain.model.Image;

/**
 * @author Gerardo De Las Cuevas
 */
public interface ImageRepository {
    Image save(Image image);
    Image getById(String id);

    Image getByImageName(String name);
}
