package com.company.payroll.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/list/base")
	public List<Department> listBaseDepartment() {
		return departmentService.listBaseDepartment();
	}
	
	@GetMapping("/list/base/information/{id}")
	public Department selectBaseDepartment(@PathVariable("id")int deptno) {
		return departmentService.getBaseDepartmentById(deptno);
	}
	
	@GetMapping("/list/detailed")
	public List<Department> listDetailedDepartment() {
		return departmentService.listDepartment();
	}
	
	@GetMapping("/list/drop-down")
	public List<Department> listDeptnoAndName() {
		return departmentService.listDeptNoAndDeptName();
	}
	
	@GetMapping("/list/count/all")
	public Integer countDepartment() {
		return departmentService.countDepartment();
	}
	
	@PostMapping("/insert")
	public Integer insertDepartment(@RequestBody Department department) {
		
		return departmentService.insertDepartment(department);
	}
	
	@PutMapping("/list/base/information/{id}/update")
	public Integer updateDepartment(@PathVariable("id")int deptno, @RequestBody Department department) {
		String deptname = department.getDeptName();
		String location = department.getLocation();
		String state = department.getState();
		String country = department.getCountry();
		int mid = department.getMId();

		Department dept = new Department(deptno, deptname, location, state, country, mid);
		
		return departmentService.updateDepartment(dept);
	}
	
	@DeleteMapping("/list/base/information/{id}/delete")
	public Integer deleteDepartment(@PathVariable("id")int id) {
		return departmentService.deleteDepartment(id);
	}

}
