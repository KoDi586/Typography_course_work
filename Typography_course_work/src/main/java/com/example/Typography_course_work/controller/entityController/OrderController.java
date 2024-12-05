package com.example.Typography_course_work.controller.entityController;

import com.example.Typography_course_work.dto.OrderDTO.create.CreateOrderRequestDto;
import com.example.Typography_course_work.dto.OrderDTO.get.AllOrderResponseDto;
import com.example.Typography_course_work.service.TypographyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class OrderController {

    private final TypographyService service;

    @PostMapping()
    public void postProduct(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        log.info("controller post product");
        service.create(createOrderRequestDto);
    }

    @GetMapping("/all")
    public ResponseEntity<AllOrderResponseDto> getAllOrder() {
        log.info("controller get all order");
        return ResponseEntity.ok(service.getAllOrders());
    }
}
