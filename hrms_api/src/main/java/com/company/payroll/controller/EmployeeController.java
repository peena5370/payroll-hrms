package com.company.payroll.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @GetMapping("user-info")
    public ResponseEntity<String> getEmployeeInfo() {
        return ResponseEntity.ok("employee info");
    }
}
