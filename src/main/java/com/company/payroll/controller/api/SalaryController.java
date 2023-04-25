package com.company.payroll.controller.api;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Salary;
import com.company.payroll.service.SalaryService;

@RestController
@RequestMapping("/salary")
public class SalaryController {
	
	@Autowired
	private SalaryService salaryService;
	
	public SalaryController(SalaryService salaryService) {
		this.salaryService = salaryService;
	}
	@GetMapping("/list")
	public ResponseEntity<List<Salary>> listSalary() {
		return ResponseEntity.ok(salaryService.getList());
	}
	
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Salary> getSalaryInfoBySapId(@PathVariable("id")int sid) {
		return ResponseEntity.ok(salaryService.getById(sid));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insertSalary(@RequestBody Salary salary) {
		Integer status = salaryService.insert(salary);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> updateSalary(@RequestBody Salary salary) {
		Integer status = salaryService.update(salary);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@PathVariable("id")int sid) {
		Integer status = salaryService.delete(sid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}

}
