package com.example.Typography_course_work.dto.provider;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderResponseDto {

    private Long id;
    private String name;
    private String material;
    private String contactInfo;

}
