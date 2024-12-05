package com.example.Typography_course_work.dto.reports.materialByOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialByOrderChildrenResponseDto {

    private Long orderId;
    private List<String> materialNames;

}
