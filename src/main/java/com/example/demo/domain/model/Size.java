package com.example.demo.domain.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Gerardo De Las Cuevas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "size")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size_name")
    private String sizeName;
    @Column(name = "available")
    private boolean available;
}
