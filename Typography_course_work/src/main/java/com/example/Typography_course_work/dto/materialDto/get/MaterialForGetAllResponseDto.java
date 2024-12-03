package com.example.Typography_course_work.dto.materialDto.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialForGetAllResponseDto {

    private List<MaterialTwoFieldResponseDto> children;

}
