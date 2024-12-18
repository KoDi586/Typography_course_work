package com.example.Typography_course_work.controller.report;

import com.example.Typography_course_work.dto.reports.materialByOrder.MaterialByOrderResponseDto;
import com.example.Typography_course_work.dto.reports.materials.MaterialReportAndTurnoverResponseDto;
import com.example.Typography_course_work.dto.reports.money.MoveMoneyTotalResponseDto;
import com.example.Typography_course_work.service.TypographyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
@CrossOrigin(origins = "http://192.168.200.78:3000")
public class ReportController {

    private final TypographyService service;

    @GetMapping("/materials")
    public ResponseEntity<MaterialReportAndTurnoverResponseDto> materialsReport() {
        return ResponseEntity.ok(service.materialReport());
    }

    @GetMapping("money")
    public ResponseEntity<MoveMoneyTotalResponseDto> moneyReport() {
        return ResponseEntity.ok(service.moneyReport());
    }

    @GetMapping("/materials_by_order")
    public ResponseEntity<MaterialByOrderResponseDto> getMaterialsByOrder() {
        return ResponseEntity.ok(service.materialByOrder());
    }
}
