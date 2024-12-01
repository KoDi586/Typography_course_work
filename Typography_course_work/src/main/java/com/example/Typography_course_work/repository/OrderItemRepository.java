package com.example.Typography_course_work.repository;

import com.example.Typography_course_work.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
