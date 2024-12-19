package com.example.Typography_course_work.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "\"order\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private Long id;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "order_items")
    private List<Integer> orderItems;
}
