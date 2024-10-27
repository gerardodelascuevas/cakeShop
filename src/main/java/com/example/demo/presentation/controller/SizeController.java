package com.example.demo.presentation.controller;

import com.example.demo.application.service.SizeService;
import com.example.demo.config.RequiredToken;
import com.example.demo.domain.model.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gerardo De Las Cuevas
 */
@RestController
@RequestMapping("/api/size")
public class SizeController {

    private final SizeService service;

    public SizeController(SizeService service) {
        this.service = service;
    }

    @RequiredToken
    @GetMapping
    public ResponseEntity<List<Size>> getAllSizes(){
        return ResponseEntity.ok(service.getAll());
    }

    @RequiredToken
    @PostMapping
    public ResponseEntity<Size> createSize(@RequestBody Size size){
        return ResponseEntity.ok(service.store(size));
    }

    @RequiredToken
    @PutMapping("/{id}")
    public ResponseEntity<Size> updateSize(@PathVariable Long id, @RequestBody Size size){
        return ResponseEntity.ok(service.update(id, size));
    }

    @RequiredToken
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSize(@PathVariable Long id){
        return ResponseEntity.ok(service.destroy(id));
    }
}
