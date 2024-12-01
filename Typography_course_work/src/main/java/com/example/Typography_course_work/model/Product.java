package com.example.Typography_course_work.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @ElementCollection
    @CollectionTable(name = "product_material", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "material_id")
    private List<Integer> materials; // Для связи через массив id

    @Column(nullable = false)
    private Integer price;

}
