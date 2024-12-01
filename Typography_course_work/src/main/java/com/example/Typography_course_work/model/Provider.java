package com.example.Typography_course_work.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

@Entity
@Table(name = "provider")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @OneToOne
    @JoinColumn(name = "material_id", unique = true, nullable = false/*, foreignKey*/)
    private Material material;
}

