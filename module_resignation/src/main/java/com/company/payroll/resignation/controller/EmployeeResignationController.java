package com.company.payroll.resignation.controller;

import com.company.payroll.resignation.service.EmployeeResignationService;
import com.company.payroll.resignation.dto.EmployeeResignationDTO;
import com.company.payroll.util.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/resignation")
public class EmployeeResignationController {

    private static final String CLASS_NAME = "[EmployeePromotionController]";
    private final EmployeeResignationService employeeResignationService;

    public EmployeeResignationController(EmployeeResignationService employeeResignationService) {
        this.employeeResignationService = employeeResignationService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse> createResignationDetail(@RequestBody EmployeeResignationDTO employeeResignationDTO) {
        return null;
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getAllResignationDetailsByOffsetAndLimitOrEmployeeId(
            @RequestParam(value = "employeeId", required = false) Long employeeId,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getResignationDetailByResignationId(@PathVariable("id") Long resignationId) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> updateResignationDetailByResignationId(@PathVariable("id") Long resignationId,
                                                                                 @RequestBody EmployeeResignationDTO employeeResignationDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteResignationDetailByResignationId(@PathVariable("id") Long resignationId) {
        return null;
    }
}
