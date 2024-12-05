package com.example.Typography_course_work.repository;

import com.example.Typography_course_work.model.MaterialsTurnover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialTurnoverRepository extends JpaRepository<MaterialsTurnover, Long> {
    List<MaterialsTurnover> findAllByMaterialId(Long id);
}
