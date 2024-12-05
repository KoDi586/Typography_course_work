package com.example.Typography_course_work.controller.entityController;

import com.example.Typography_course_work.dto.productDTO.get.AllProductResponseDto;
import com.example.Typography_course_work.dto.productDTO.get.ProductResponseDto;
import com.example.Typography_course_work.dto.productDTO.post.ProductRequestDto;
import com.example.Typography_course_work.service.TypographyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class ProductController {

    private final TypographyService service;

    @GetMapping("/all")
    public ResponseEntity<AllProductResponseDto> getAll() {
        log.info("controller get all");
        return ResponseEntity.ok(service.getAllProducts());
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getById(@RequestParam Long productId) {
        return ResponseEntity.ok(service.getProductByProductId(productId));
    }


    @PutMapping()
    public ResponseEntity<Void> setProduct() {
        return ResponseEntity.ok().build();
    }


    @PostMapping()
    public ResponseEntity<Void> postProduct(@RequestBody ProductRequestDto product) {
        service.saveProduct(product);
        return ResponseEntity.ok().build();
    }
}
