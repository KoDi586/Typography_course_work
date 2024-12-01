package com.example.Typography_course_work.repository;

import com.example.Typography_course_work.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    Order saveByParams(Long id, Long clientId, List<Integer> orderItems);
}
