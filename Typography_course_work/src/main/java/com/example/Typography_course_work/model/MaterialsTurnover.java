package com.example.Typography_course_work.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "materials_turnover")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialsTurnover {

    @Id
    private Long id;
    @Column(name = "material_id")
    private Long materialId;
    private Integer count;
}
