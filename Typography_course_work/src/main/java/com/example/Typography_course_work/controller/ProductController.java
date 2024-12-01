package com.example.Typography_course_work.controller;

import com.example.Typography_course_work.dto.OrderDTO.CreateOrderRequestDto;
import com.example.Typography_course_work.dto.productDTO.AllProductResponseDto;
import com.example.Typography_course_work.service.TypographyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final TypographyService service;

    @GetMapping("/all")
    public ResponseEntity<AllProductResponseDto> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Void> getById(Long productId) {
        return ResponseEntity.ok().build();
    }


    @PutMapping()
    public ResponseEntity<Void> setProduct() {
        return ResponseEntity.ok().build();
    }


    @PostMapping()
    public ResponseEntity<Void> postProduct() {
        return ResponseEntity.ok().build();
    }
}
