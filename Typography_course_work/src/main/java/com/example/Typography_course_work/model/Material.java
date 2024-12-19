package com.example.Typography_course_work.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "material")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    private Long id;
    private String title;
    private Integer count;
    @Column(name = "count_of_spent")
    private Integer countOfSpent;
    private Integer price;
}
