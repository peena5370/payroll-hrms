package com.company.payroll.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.PayrollManager;
import com.company.payroll.service.PayrollManagerService;

@RestController
@RequestMapping("/payroll/manager")
public class PayrollManagerController {
	
	private PayrollManagerService payrollManagerService;
	
	public PayrollManagerController(PayrollManagerService payrollManagerService) {
		this.payrollManagerService = payrollManagerService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<PayrollManager>> listPayrollManager() {
		return ResponseEntity.ok(payrollManagerService.getList());
	}
	
	@GetMapping("/{id}/list")
	public ResponseEntity<List<PayrollManager>> listPayrollManagerByMId(@PathVariable("id") int mid) {
		return ResponseEntity.ok(payrollManagerService.getListByMId(mid));
	}
	
	@GetMapping("/list/information/{id}")
	public ResponseEntity<PayrollManager> getById(@PathVariable("id")int prMgrId) {
		return ResponseEntity.ok(payrollManagerService.getById(prMgrId));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody PayrollManager payrollManager) {
		Integer status = payrollManagerService.insert(payrollManager);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody PayrollManager payrollManager) {
		Integer status = payrollManagerService.update(payrollManager);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@PathVariable("id")int prMgrId) {
		Integer status = payrollManagerService.delete(prMgrId);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
