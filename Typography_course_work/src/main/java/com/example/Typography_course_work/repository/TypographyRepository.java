package com.example.Typography_course_work.repository;

import com.example.Typography_course_work.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypographyRepository extends JpaRepository<Product, Long> {
}
