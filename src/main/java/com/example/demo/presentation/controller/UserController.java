package com.example.demo.presentation.controller;

import com.example.demo.config.RequiredToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gerardo De Las Cuevas
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping
    @RequiredToken
    public String hello(){
        return "hello";
    }

}
