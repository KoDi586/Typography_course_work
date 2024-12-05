package com.example.Typography_course_work.dto.reports.money;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveMoneyResponseDto {

    private String thing;  // материал нейм или продукт нейм
    private String people;  // поставщик или клиент
    private Integer price;
    private String type;  // потрачено или заработано

}
