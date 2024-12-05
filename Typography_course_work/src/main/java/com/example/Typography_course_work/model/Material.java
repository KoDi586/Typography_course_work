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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String title;

    @Column()
    private Integer count;

    @Column(name = "count_of_spent")
    private Integer countOfSpent;

    @Column()
    private Integer price;

}
