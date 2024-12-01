package com.example.Typography_course_work.dto.OrderDTO.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDto {

    private String product;
    private Integer count;

}
