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
    private Long id;
    private String name;
    @Column(name = "second_name")
    private String secondName;
    private String email;
    private String phone;
    private String card;
}
