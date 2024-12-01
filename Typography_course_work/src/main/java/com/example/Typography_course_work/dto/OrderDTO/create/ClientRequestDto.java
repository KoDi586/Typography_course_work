package com.example.Typography_course_work.dto.OrderDTO.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {

    private String name;
    private String secondName;
    private String email;
    private String phone;
    private String card;

}
