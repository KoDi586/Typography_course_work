package com.example.Typography_course_work.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private Long id;
    private String title;
    @Column(name = "materials")
    private List<Integer> materials;
    private Integer price;
}
