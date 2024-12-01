package com.example.Typography_course_work.model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false/*, onDelete = ForeignKeyAction.CASCADE*/)
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true, nullable = false/*, onDelete = ForeignKeyAction.CASCADE*/)
    private Product product;

    @Column(nullable = false)
    private Integer count;
}
