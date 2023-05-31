package com.company.payroll.controller.api;

import java.util.List;
import java.util.Optional;

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

import com.company.payroll.model.PayrollEmployee;
import com.company.payroll.service.StaffPayrollService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/payroll/employee")
public class PayrollEmployeeController {
	
	@Autowired
	private StaffPayrollService staffPayrollService;

	@Operation(summary="Get employee payroll list")
	@GetMapping
	public ResponseEntity<PageInfo<PayrollEmployee>> listPayrollEmployee(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffPayrollService.listPayrollEmployee(page, offset));
	}
	
	@Operation(summary="Get payroll list by employee id")
	@GetMapping("/{id}/all")
	public ResponseEntity<Optional<List<PayrollEmployee>>> listPayrollEmployeeByEId(@Parameter(description="employee id") @PathVariable("id")int eid) {
		return ResponseEntity.ok(staffPayrollService.findPayrollEmployeeByEId(eid));
	}
	
	@Operation(summary="Get employee payroll list by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<PayrollEmployee>> getById(@PathVariable("id") int id) {
		return ResponseEntity.ok(staffPayrollService.findPayrollEmployeeById(id));
	}
	
	@Operation(summary="Insert employee payroll info")
	@PostMapping
	public ResponseEntity<PayrollEmployee> insert(@RequestBody PayrollEmployee payrollEmployee) {
		return ResponseEntity.ok(staffPayrollService.insertPayrollEmployee(payrollEmployee));
	}
	
	@Operation(summary="Update employee payroll info.")
	@PutMapping("/{id}")
	public ResponseEntity<PayrollEmployee> update(@RequestBody PayrollEmployee payrollEmployee) {
		return ResponseEntity.ok(staffPayrollService.updatePayrollEmployee(payrollEmployee));
	}
	
	@Operation(summary="Delete employee payroll info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int prid) {		
		return ResponseEntity.ok(staffPayrollService.deletePayrollEmployee(prid));
	}
}
