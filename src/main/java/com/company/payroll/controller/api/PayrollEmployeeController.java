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

import com.company.payroll.model.PayrollEmployee;
import com.company.payroll.service.PayrollEmployeeService;

@RestController
@RequestMapping("/payroll/employee")
public class PayrollEmployeeController {
	
	private PayrollEmployeeService payrollEmployeeService;
	
	public PayrollEmployeeController(PayrollEmployeeService payrollEmployeeService) {
		this.payrollEmployeeService = payrollEmployeeService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<PayrollEmployee>> listPayrollEmployee() {
		return ResponseEntity.ok(payrollEmployeeService.getList());
	}
	
	@GetMapping("/{id}/list")
	public ResponseEntity<List<PayrollEmployee>> listPayrollEmployeeByEId(@PathVariable("id")int eid) {
		return ResponseEntity.ok(payrollEmployeeService.getListByEId(eid));
	}
	
	@GetMapping("/list/information/{id}")
	public ResponseEntity<PayrollEmployee> getById(@PathVariable("id")int id) {
		return ResponseEntity.ok(payrollEmployeeService.getById(id));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody PayrollEmployee payrollEmployee) {
		Integer status = payrollEmployeeService.insert(payrollEmployee);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody PayrollEmployee payrollEmployee) {
		Integer status = payrollEmployeeService.update(payrollEmployee);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@PathVariable("id")int prid) {
		Integer status = payrollEmployeeService.delete(prid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
//	@GetMapping("/list/information/date/{id}")
//	public List<Payroll> getPayrollDateBySapId(@PathVariable("id")int sapid) {
//		return payrollService.listPaymentDateAndSapId(sapid);
//	}
//	
//	@GetMapping("/employee/{date}/{id}")
//	public Payroll getPaySlipBySapId(@PathVariable("id")int sapid, @PathVariable("date")String date) {
//		
//		return payrollService.getPayslip(sapid, DateTimeUtils.stringToFormatDate(date));
//	}
}
