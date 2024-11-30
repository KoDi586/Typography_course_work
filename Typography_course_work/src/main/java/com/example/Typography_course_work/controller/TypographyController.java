package com.example.Typography_course_work.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class TypographyController {

    @GetMapping("/all")
    public ResponseEntity<Void> getAll() {
        return ResponseEntity.ok().build();
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
