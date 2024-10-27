package com.example.demo.presentation.controller;

import com.example.demo.application.service.FlavorService;
import com.example.demo.config.RequiredToken;
import com.example.demo.domain.model.Flavor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gerardo De Las Cuevas
 */
@RestController
@RequestMapping(value = "/api/flavors")
public class FlavorController {

    private final FlavorService service;

    public FlavorController(FlavorService service) {
        this.service = service;
    }

    @RequiredToken
    @GetMapping
    public ResponseEntity<List<Flavor>> getFlavors(){
        return ResponseEntity.ok(service.getAll());
    }

    @RequiredToken
    @PostMapping
    public ResponseEntity<Flavor> createNewFlavor(@RequestBody Flavor flavor){
        return ResponseEntity.ok(service.create(flavor));
    }

    @RequiredToken
    @PutMapping(value = "{id}")
    public ResponseEntity<String> updateFlavor(@RequestBody String name, @PathVariable Long id){
        return ResponseEntity.ok(service.update(id, name));
    }

    @RequiredToken
    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> destroyFlavor(@PathVariable Long id){
        return ResponseEntity.ok(service.destroy(id));
    }

}
