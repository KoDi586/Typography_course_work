package com.example.Typography_course_work.dto.OrderDTO.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllOrderResponseDto {

    private List<OrderResponseDto> children;

}