package com.example.Typography_course_work.repository;

import com.example.Typography_course_work.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findByTitle(String title);
}
