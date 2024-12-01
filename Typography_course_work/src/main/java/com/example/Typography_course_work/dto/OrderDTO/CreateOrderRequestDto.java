package com.example.Typography_course_work.dto.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestDto {

    private ClientRequestDto client;
    private List<OrderItemRequestDto> orderItems;

}
