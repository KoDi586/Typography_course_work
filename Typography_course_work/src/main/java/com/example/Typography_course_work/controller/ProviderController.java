package com.example.Typography_course_work.controller;

import com.example.Typography_course_work.dto.provider.ProviderAllResponseDto;
import com.example.Typography_course_work.service.TypographyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/provider")
@CrossOrigin(origins = "http://localhost:3000")
public class ProviderController {

    private final TypographyService service;

    @GetMapping("/all")
    public ResponseEntity<ProviderAllResponseDto> getAllProviders() {
        return ResponseEntity.ok(service.getAllProviders());
    }

}
