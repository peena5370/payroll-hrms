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

import com.company.payroll.model.StaffSalary;
import com.company.payroll.service.StaffSalaryService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/staff/salary")
public class StaffSalaryController {
	
	@Autowired
	private StaffSalaryService staffSalaryService;
	
	@Operation(summary="Get salary list")
	@GetMapping
	public ResponseEntity<PageInfo<StaffSalary>> list(@RequestParam(value="page", required=true) int page,
													  @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffSalaryService.list(page, offset));
	}
	
	@Operation(summary="Get salary info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StaffSalary>> findById(@PathVariable("id") Integer sId) {
		return ResponseEntity.ok(staffSalaryService.findById(sId));
	}
	
	@Operation(summary="Update staffSalary info.")
	@PutMapping("/{id}")
	public ResponseEntity<StaffSalary> update(@RequestBody StaffSalary staffSalary) {
		return ResponseEntity.ok(staffSalaryService.update(staffSalary));
	}
}
