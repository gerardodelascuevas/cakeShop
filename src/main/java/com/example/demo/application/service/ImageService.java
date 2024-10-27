package com.example.demo.application.service;

import com.example.demo.domain.model.Image;
import com.example.demo.domain.repository.ImageRepository;
import com.example.demo.infraestructure.persistence.repository.ImageMongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Gerardo De Las Cuevas
 */
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageMongoRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String saveImage(MultipartFile file) {
            Image image = new Image();
            try {
                image.setData(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error when processing the image", e);
            }
            image.setImageName(file.getOriginalFilename());
            Image newImage = imageRepository.save(image);
            return newImage.getImageName();
        }

    public Image getImageById(String id) {
        Image image =  imageRepository.getById(id);
        if(image == null){
            throw new IllegalArgumentException("Image not found");
        }
        return image;
    }

    public Image getImageByName(String name){
        Image image = imageRepository.getByImageName(name);
        if(image == null){
            throw new IllegalArgumentException("Image not found");
        }
        return image;
    }
}
