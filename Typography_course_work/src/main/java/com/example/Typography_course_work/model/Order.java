package com.example.Typography_course_work.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id"/*, nullable = false/*, onDelete = ForeignKeyAction.CASCADE*/)
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL/*, orphanRemoval = true*/)
    private List<OrderItem> orderItems;
}
