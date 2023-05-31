package com.company.payroll.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Salary;
import com.company.payroll.service.SalaryService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {
	
	@Autowired
	private SalaryService salaryService;
	
	@Operation(summary="Get salary list")
	@GetMapping
	public ResponseEntity<PageInfo<Salary>> listSalary(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(salaryService.list(page, offset));
	}
	
	@Operation(summary="Get salary info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Salary>> getById(@PathVariable("id") int sid) {
		return ResponseEntity.ok(salaryService.findById(sid));
	}
	
	@Operation(summary="Update salary info.")
	@PutMapping("/{id}")
	public ResponseEntity<Salary> update(@RequestBody Salary salary) {
		return ResponseEntity.ok(salaryService.update(salary));
	}
}
