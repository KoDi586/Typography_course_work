package com.example.Typography_course_work.dto.reports.money;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveMoneyTotalResponseDto {

    List<MoveMoneyResponseDto> children;

}
