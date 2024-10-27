package com.example.demo.infraestructure.persistence.repository;

import com.example.demo.domain.model.Image;
import com.example.demo.domain.repository.ImageRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gerardo De Las Cuevas
 */
@Repository
public interface ImageMongoRepository extends MongoRepository<Image, String>, ImageRepository {

}
