package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.DepartmentMapper;
import com.company.payroll.model.Department;
import com.company.payroll.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public List<Department> getList() {
		return departmentMapper.selectList();
	}

	@Override
	public Department getById(int deptno) {
		return departmentMapper.selectByPrimaryKey(deptno);
	}

	@Override
	public Integer insert(Department department) {
		return departmentMapper.insertSelective(department);
	}

	@Override
	public Integer update(Department department) {
		return departmentMapper.updateByPrimaryKeySelective(department);
	}

	@Override
	public Integer delete(int deptno) {
		return departmentMapper.deleteByPrimaryKey(deptno);
	}
}
