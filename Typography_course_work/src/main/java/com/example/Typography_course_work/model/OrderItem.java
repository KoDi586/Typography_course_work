package com.example.Typography_course_work.model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;
    private Integer count;
}
