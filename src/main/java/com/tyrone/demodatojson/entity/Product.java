package com.tyrone.demodatojson.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String SKU;
    private String description;
    private float price;
    private int stock;

    @Column(name = "features", columnDefinition = "json")
    private String  features; // aqui front puede crear un formulario con cualquier campo y si es posible q no se guarden los campos q no se rellenen para haci poder tener muchos campos y ocupar lo q el producto requiera

    @ManyToOne
    private Category category;
}
