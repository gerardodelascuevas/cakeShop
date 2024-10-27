package com.example.demo.domain.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Gerardo De Las Cuevas
 */
@Getter
@Setter

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    public Client() {
    }

    public Client(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}