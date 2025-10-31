package com.company.payroll.promotion.controller;

import com.company.payroll.promotion.dto.EmployeePromotionDTO;
import com.company.payroll.promotion.service.EmployeePromotionService;
import com.company.payroll.util.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/promotion")
public class EmployeePromotionController {

    private static final String CLASS_NAME = "[EmployeePromotionController]";
    private final EmployeePromotionService employeePromotionService;

    public EmployeePromotionController(EmployeePromotionService employeePromotionService) {
        this.employeePromotionService = employeePromotionService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse> createPromotionDetail(@RequestBody EmployeePromotionDTO employeePromotionDTO) {
        return null;
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getAllPromotionDetailByOffsetAndLimit(
            @RequestParam(value = "employeeId", required = false) Long employeeId,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getPromotionDetailByPromotionId(@PathVariable("id") Long promotionId) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> updatePromotionDetailByPromotionId(@PathVariable("id") Long promotionId, @RequestBody EmployeePromotionDTO employeePromotionDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deletePromotionDetailByPromotionId(@PathVariable("id") Long promotionId) {
        return null;
    }

    @GetMapping("/employee-detail")
    public ResponseEntity<CommonResponse> getAllPromotionDetailsByEmployeeId() {
        return null;
    }
}
