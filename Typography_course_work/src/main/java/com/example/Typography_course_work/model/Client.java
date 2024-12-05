package com.example.Typography_course_work.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column()
    private String email;

    @Column()
    private String phone;

    @Column()
    private String card;
}
