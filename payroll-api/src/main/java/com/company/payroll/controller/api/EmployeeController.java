package com.company.payroll.controller.api;

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

import com.company.payroll.model.Employee;
import com.company.payroll.service.StaffDetailsService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private StaffDetailsService staffDetailsService;
	
	@Operation(summary="Get employee list")
	@GetMapping
	public ResponseEntity<PageInfo<Employee>> listEmployee(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffDetailsService.listEmployee(page, offset));
	}
	
	@Operation(summary="Get employee info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Employee>> getById(@Parameter() @PathVariable("id") int eid) {
		return ResponseEntity.ok(staffDetailsService.findEmployeeById(eid));
	}

	@Operation(summary="Insert employee info")
	@PostMapping
	public ResponseEntity<Employee> insert(@RequestBody Employee employee) {
		return ResponseEntity.ok(staffDetailsService.registerEmployee(employee));
	}
	
	@Operation(summary="Update employee info.")
	@PutMapping("/{id}")
	public ResponseEntity<Employee> update(@RequestBody Employee employee) {
		return ResponseEntity.ok(staffDetailsService.updateEmployee(employee));
	}
	
	@Operation(summary="Delete employee info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int eid) {
		return ResponseEntity.ok(staffDetailsService.deleteEmployee(eid));
	}
	
//	@GetMapping("/list/count/all")
//	public Integer countEmployee() {
//		return employeeService.countEmployee();
//	}
	
//	@GetMapping("/list/count/active")
//	public Integer countAvailableEmployee() {
//		return employeeService.countAvailableEmployee();
//	}
}