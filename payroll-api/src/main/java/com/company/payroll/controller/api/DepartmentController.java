package com.company.payroll.controller.api;

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

import com.company.payroll.model.Department;
import com.company.payroll.service.CompanyInfoService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

	@Autowired
	private CompanyInfoService companyInfoService;
	
	@Operation(summary="Get department list")
	@GetMapping
	public ResponseEntity<PageInfo<Department>> listDepartment(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(companyInfoService.listDepartment(page, offset));
	}
	
	@Operation(summary="Get department info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Department>> getById(@PathVariable("id") int deptno) {
		return ResponseEntity.ok(companyInfoService.findDepartmentById(deptno));
	}
	
	@Operation(summary="Insert department info")
	@PostMapping
	public ResponseEntity<Department> insert(@RequestBody Department department) {
		Department status = companyInfoService.insertDepartment(department);
		if(status==null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status); 
	}
	
	@Operation(summary="Update department info.")
	@PutMapping("/{id}")
	public ResponseEntity<Department> update(@RequestBody Department department) {	
		return ResponseEntity.ok(companyInfoService.updateDepartment(department)); 
	}
	
	@Operation(summary="Delete department info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int deptno) {		
		return ResponseEntity.ok(companyInfoService.deleteDepartment(deptno)); 
	}
}
