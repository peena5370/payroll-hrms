package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.EmployeeMapper;
import com.company.payroll.model.Employee;
import com.company.payroll.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public PageInfo<Employee> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Employee> list = employeeMapper.selectList();
		return new PageInfo<Employee>(list);
	}

	@Override
	public Employee getById(int eid) {
		return employeeMapper.selectByPrimaryKey(eid);
	}

	@Override
	public Integer insert(Employee emp) {
		return employeeMapper.insertSelective(emp);
	}
	
	@Override
	public Integer update(Employee emp) {
		return employeeMapper.updateByPrimaryKeySelective(emp);
	}
	
	@Override
	public Integer delete(int eid) {
		return employeeMapper.deleteByPrimaryKey(eid);
	}
//	@Override
//	public Integer countEmployee() {
//		// TODO Auto-generated method stub
//		return employeeMapper.countEmployee();
//	}
//
//	@Override
//	public Integer countAvailableEmployee() {
//		// TODO Auto-generated method stub
//		return employeeMapper.countAvailableEmployee();
//	}
}
