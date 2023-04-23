package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.DepartmentMapper;
import com.company.payroll.model.Department;
import com.company.payroll.service.DepartmentService;

//@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public List<Department> listBaseDepartment() {
		// TODO Auto-generated method stub
		return departmentMapper.selectDepartment();
	}

	@Override
	public Department getBaseDepartmentById(int deptno) {
		// TODO Auto-generated method stub
		return departmentMapper.selectDepartmentbyId(deptno);
	}

	@Override
	public List<Department> listDepartment() {
		// TODO Auto-generated method stub
		return departmentMapper.selectDeptList();
	}

	@Override
	public List<Department> listDeptNoAndDeptName() {
		// TODO Auto-generated method stub
		return departmentMapper.selectDeptnoAndName();
	}

	@Override
	public Integer countDepartment() {
		// TODO Auto-generated method stub
		return departmentMapper.countDept();
	}

	@Override
	public Integer insertDepartment(Department dept) {
		// TODO Auto-generated method stub
		return departmentMapper.insertDept(dept);
	}

	@Override
	public Integer updateDepartment(Department dept) {
		// TODO Auto-generated method stub
		return departmentMapper.updateDept(dept);
	}

	@Override
	public Integer deleteDepartment(int deptno) {
		// TODO Auto-generated method stub
		return departmentMapper.deleteDept(deptno);
	}

}
