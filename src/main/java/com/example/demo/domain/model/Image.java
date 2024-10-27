package com.example.demo.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * @author Gerardo De Las Cuevas
 */
@Data
@Getter
@Setter
@Document(collection = "cakeImages")
public class Image {
    @Id
    private String id;
    private String imageName;
    private byte[] data;
}
