package com.example.Typography_course_work.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "provider")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    @Id
    private Long id;
    private String name;
    @Column(name = "material_id")
    private Long materialId;
    @Column(name = "contact_info")
    private String contactInfo;
}

