package com.example.demo.presentation.controller;

import com.example.demo.application.service.ImageService;
import com.example.demo.domain.model.Image;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author Gerardo De Las Cuevas
 */
@RestController
@RequestMapping(value = "/api/image")
public class ImageController {
    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(
            @RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok(service.saveImage(image));
        }

    @GetMapping(value = "/{id}")
    public ResponseEntity<byte []> image(@PathVariable String id){
        Image image = service.getImageById(id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getImageName() + "\"")
                    .body(image.getData());
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<byte []> viewImage(@PathVariable String id){
        Image image = service.getImageById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image.getData());
    }
}