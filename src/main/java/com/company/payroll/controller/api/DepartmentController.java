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

import com.company.payroll.model.Department;
import com.company.payroll.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private DepartmentService departmentService;
	
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Department>> listDepartment() {
		return ResponseEntity.ok(departmentService.getList());
	}
	
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Department> selectBaseDepartment(@PathVariable("id")int deptno) {
		return ResponseEntity.ok(departmentService.getById(deptno));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody Department department) {
		Integer status = departmentService.insert(department);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status); 
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Department department) {
		Integer status = departmentService.update(department);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status); 
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@PathVariable("id")int deptno) {
		Integer status = departmentService.delete(deptno);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status); 
	}
	
//	@GetMapping("/list/count/all")
//	public Integer countDepartment() {
//		return departmentService.countDepartment();
//	}
}
