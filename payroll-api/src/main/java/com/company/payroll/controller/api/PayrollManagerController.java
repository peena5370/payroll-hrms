package com.company.payroll.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.company.payroll.model.PayrollManager;
import com.company.payroll.service.StaffPayrollService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/payroll/manager")
public class PayrollManagerController {
	@Autowired
	private StaffPayrollService staffPayrollService;

	@Operation(summary="Get manager payroll list")
	@GetMapping
	public ResponseEntity<PageInfo<PayrollManager>> listPayrollManager(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffPayrollService.listPayrollManager(page, offset));
	}
	
	@Operation(summary="Get payroll list by manager id")
	@GetMapping("/{id}/all")
	public ResponseEntity<Optional<List<PayrollManager>>> listPayrollManagerByMId(@Parameter(description="Manager id") @PathVariable("id") int mid) {
		return ResponseEntity.ok(staffPayrollService.findPayrollManagerByMId(mid));
	}
	
	@Operation(summary="Get manager payroll info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<PayrollManager>> getById(@PathVariable("id") int prMgrId) {
		return ResponseEntity.ok(staffPayrollService.findPayrollManagerById(prMgrId));
	}
	
	@Operation(summary="Insert manager payroll info")
	@PostMapping
	public ResponseEntity<PayrollManager> insert(@RequestBody PayrollManager payrollManager) {
		return ResponseEntity.ok(staffPayrollService.insertPayrollManager(payrollManager));
	}
	
	@Operation(summary="Update manager payroll info.")
	@PutMapping("/{id}")
	public ResponseEntity<PayrollManager> update(@RequestBody PayrollManager payrollManager) {
		return ResponseEntity.ok(staffPayrollService.updatePayrollManager(payrollManager));
	}
	
	@Operation(summary="Delete manager payroll info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int prMgrId) {
		Integer status = staffPayrollService.deletePayrollManager(prMgrId);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
