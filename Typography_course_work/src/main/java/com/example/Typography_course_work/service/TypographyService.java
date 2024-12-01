package com.example.Typography_course_work.service;

import com.example.Typography_course_work.dto.OrderDTO.CreateOrderRequestDto;
import com.example.Typography_course_work.dto.productDTO.AllProductResponseDto;
import com.example.Typography_course_work.dto.productDTO.ProductResponseDto;
import com.example.Typography_course_work.model.Product;
import com.example.Typography_course_work.repository.TypographyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TypographyService {
    private final TypographyRepository repository;

    public void create(CreateOrderRequestDto createOrderRequestDto) {
        log.info(createOrderRequestDto.toString());
    }

    public AllProductResponseDto getAll() {
        List<Product> all = repository.findAll();
        return convertToAllDto(all);
    }

    private AllProductResponseDto convertToAllDto(List<Product> all) {
        List<ProductResponseDto> children = new ArrayList<>();
        for (Product product : all) {
            children.add(
                    new ProductResponseDto(
                            product.getId().toString(),
                            product.getTitle(),
                            product.getPrice()
                    )
            );
        }
        return new AllProductResponseDto(children);
    }
}
