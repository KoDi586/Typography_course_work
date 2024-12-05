package com.example.Typography_course_work.dto.reports.materialByOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialByOrderResponseDto {
    List<MaterialByOrderChildrenResponseDto> children;
}
