package com.example.Typography_course_work.dto.productDTO.post;

import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private Long Id;
    private String title;
    private List<String> materials;
    private Integer price_with_materials;

}
