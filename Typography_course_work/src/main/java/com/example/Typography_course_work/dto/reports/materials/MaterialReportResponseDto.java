package com.example.Typography_course_work.dto.reports.materials;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialReportResponseDto {
    private String name;
    private Integer countInWarehouse;  // количество на складе
    private Integer countOfSpent;  // количество потраченого
    private Integer countOfBought;  // сколько куплено
    private Integer totalSpentMoney; // сколько отдали поставщикам всего
}
