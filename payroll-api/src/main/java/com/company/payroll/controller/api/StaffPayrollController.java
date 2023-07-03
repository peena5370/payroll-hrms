package com.company.payroll.controller.api;

import java.util.List;
import java.util.Optional;

import com.company.payroll.model.StaffPayroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.service.StaffPayrollService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/staff/payroll")
public class StaffPayrollController {
	
	@Autowired
	private StaffPayrollService staffPayrollService;

	@Operation(summary="Get employee payroll list")
	@GetMapping
	public ResponseEntity<PageInfo<StaffPayroll>> list(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffPayrollService.listStaffPayroll(page, offset));
	}
	
	@Operation(summary="Get payroll list by employee id")
	@GetMapping("/{id}/all")
	public ResponseEntity<List<StaffPayroll>> listPayrollByStaffId(@Parameter(description="employee id") @PathVariable("id") Integer staffId) {
		return ResponseEntity.ok(staffPayrollService.findStaffPayrollByStaffId(staffId));
	}
	
	@Operation(summary="Get employee payroll list by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StaffPayroll>> findById(@PathVariable("id") Integer prId) {
		return ResponseEntity.ok(staffPayrollService.findStaffPayrollById(prId));
	}
	
	@Operation(summary="Insert employee payroll info")
	@PostMapping
	public ResponseEntity<StaffPayroll> insert(@RequestBody StaffPayroll staffPayroll) {
		return ResponseEntity.ok(staffPayrollService.insertStaffPayroll(staffPayroll));
	}
	
	@Operation(summary="Update employee payroll info.")
	@PutMapping("/{id}")
	public ResponseEntity<StaffPayroll> update(@RequestBody StaffPayroll staffPayroll) {
		return ResponseEntity.ok(staffPayrollService.updateStaffPayroll(staffPayroll));
	}
	
	@Operation(summary="Delete employee payroll info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") Integer prId) {
		return ResponseEntity.ok(staffPayrollService.deleteStaffPayroll(prId));
	}
}
