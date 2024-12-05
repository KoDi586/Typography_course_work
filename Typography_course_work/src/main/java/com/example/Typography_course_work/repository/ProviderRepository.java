package com.example.Typography_course_work.repository;

import com.example.Typography_course_work.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Provider findByMaterialId(Long materialId);
}
