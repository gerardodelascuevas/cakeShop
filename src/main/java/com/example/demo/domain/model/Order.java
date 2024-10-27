package com.example.demo.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Gerardo De Las Cuevas
 */
@Data
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "order_flavors",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "flavor_id")
    )
    private List<Flavor> flavors;

    @Column(name = "description")
    private String description;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "cake_size")
    private String cakeSize;

    @Column(name = "legend")
    private String legend;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name="image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Order(List<Flavor> flavors, String description, OrderStatus orderStatus, String cakeSize, String legend, Client client, String image) {
        this.flavors = flavors;
        this.description = description;
        this.orderStatus = orderStatus;
        this.cakeSize = cakeSize;
        this.legend = legend;
        this.client = client;
        this.image = image;
    }


    public Order() {

    }

}
