package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author Gerardo De Las Cuevas
 */
@Data
@Entity
@Getter
@Table(name = "flavor")
public class Flavor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "flavors")
    @JsonIgnore
    private List<Order> orders;
}
