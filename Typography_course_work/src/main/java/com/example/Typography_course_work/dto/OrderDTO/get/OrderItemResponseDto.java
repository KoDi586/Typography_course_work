package com.example.Typography_course_work.dto.OrderDTO.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDto {

    private String productName;
    private Integer count;

}
