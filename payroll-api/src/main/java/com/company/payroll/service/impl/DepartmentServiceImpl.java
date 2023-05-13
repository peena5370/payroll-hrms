package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.DepartmentMapper;
import com.company.payroll.model.Department;
import com.company.payroll.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public PageInfo<Department> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Department> list = departmentMapper.selectList();
		return new PageInfo<Department>(list);
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
