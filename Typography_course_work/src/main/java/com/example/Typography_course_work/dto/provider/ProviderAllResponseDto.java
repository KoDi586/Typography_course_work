package com.example.Typography_course_work.dto.provider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderAllResponseDto {

    private List<ProviderResponseDto> children;

}
