package com.example.Typography_course_work.controller;

import com.example.Typography_course_work.dto.OrderDTO.create.CreateOrderRequestDto;
import com.example.Typography_course_work.dto.OrderDTO.get.AllOrderResponseDto;
import com.example.Typography_course_work.dto.OrderDTO.get.OrderResponseDto;
import com.example.Typography_course_work.service.TypographyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final TypographyService service;

    @PostMapping()
    public void postProduct(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        service.create(createOrderRequestDto);
    }

    @GetMapping("/all")
    public ResponseEntity<AllOrderResponseDto> getAllOrder() {
        return ResponseEntity.ok(service.getAllOrders());
    }
}
