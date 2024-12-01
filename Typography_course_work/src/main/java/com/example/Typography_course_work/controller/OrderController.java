package com.example.Typography_course_work.controller;

import com.example.Typography_course_work.dto.OrderDTO.CreateOrderRequestDto;
import com.example.Typography_course_work.service.TypographyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final TypographyService service;

    @PostMapping()
    public void postProduct(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        service.create(createOrderRequestDto);
    }

}
