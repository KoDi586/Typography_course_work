package com.example.Typography_course_work.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllProductResponseDto {

    private List<ProductResponseDto> children;

}
