package com.company.payroll.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.BankingInfoMapper;
import com.company.payroll.mapper.EmployeeMapper;
import com.company.payroll.mapper.ManagerMapper;
import com.company.payroll.mapper.SalaryMapper;
import com.company.payroll.model.BankingInfo;
import com.company.payroll.model.Employee;
import com.company.payroll.model.Manager;
import com.company.payroll.model.Salary;
import com.company.payroll.service.StaffDetailsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StaffDetailsServiceImpl implements StaffDetailsService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private ManagerMapper managerMapper;
	
	@Autowired
	private BankingInfoMapper bankingInfoMapper;
	
	@Autowired
	private SalaryMapper salaryMapper;

	@Override
	public Integer deleteEmployee(int eid) {
		Integer row = 0;
		Employee info = employeeMapper.selectByPrimaryKey(eid);
		Integer one = employeeMapper.deleteByPrimaryKey(info.getEId());
		Integer two = bankingInfoMapper.deleteByPrimaryKey(info.getBId());
		Integer three = salaryMapper.deleteByPrimaryKey(info.getSId());
		
		if(one==1&&two==1&&three==1) {
			row = 1;
		} else {
			throw new RuntimeException();
		}
		
		return row;
	}

	@Override
	public Integer deleteManager(int mid) {
		Integer row = 0;
		
		Manager info = managerMapper.selectByPrimaryKey(mid);
		Integer one1 = managerMapper.deleteByPrimaryKey(info.getMId());
		Integer two2 = bankingInfoMapper.deleteByPrimaryKey(info.getBId());
		Integer three3 = salaryMapper.deleteByPrimaryKey(info.getSId());
		
		if(one1==1&&two2==1&&three3==1) {
			row = 1;
		} else {
			throw new RuntimeException();
		}
		
		return row;
	}

	@Override
	public Optional<Employee> findEmployeeById(int eid) {
		return Optional.ofNullable(employeeMapper.selectByPrimaryKey(eid));
	}

	@Override
	public Optional<Manager> findManagerById(int mid) {
		return Optional.ofNullable(managerMapper.selectByPrimaryKey(mid));
	}

	@Override
	public PageInfo<Employee> listEmployee(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Employee> list = employeeMapper.selectList();
		return new PageInfo<Employee>(list);
	}

	@Override
	public PageInfo<Manager> listManager(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Manager> list = managerMapper.selectList();
		return new PageInfo<Manager>(list);
	}

	@Override
	public Employee registerEmployee(Employee employee) {
		bankingInfoMapper.insertSelective(employee.getBankingInfo());
		BankingInfo info = bankingInfoMapper.selectByPrimaryKey(employee.getBankingInfo().getBId());
		
		salaryMapper.insertSelective(employee.getSalary());
		Salary salary = salaryMapper.selectByPrimaryKey(employee.getSalary().getSId());
		employee.setBId(info.getBId());
		employee.setSId(salary.getSId());
		Integer row = employeeMapper.insertSelective(employee);
		if(info==null || salary==null || row==0) {
			throw new RuntimeException();
		}
		
		return employee;
	}

	@Override
	public Manager registerManager(Manager manager) {
		bankingInfoMapper.insertSelective(manager.getBankingInfo());
		BankingInfo info = bankingInfoMapper.selectByPrimaryKey(manager.getBankingInfo().getBId());
		
		salaryMapper.insertSelective(manager.getSalary());
		Salary salary = salaryMapper.selectByPrimaryKey(manager.getSalary().getSId());
		
		manager.setBId(info.getBId());
		manager.setSId(salary.getSId());
		Integer row = managerMapper.insertSelective(manager);

		if(info==null || salary==null || row==0) {
			throw new RuntimeException();
		}
		
		return manager;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
		return employee;
	}

	@Override
	public Manager updateManager(Manager manager) {
		managerMapper.updateByPrimaryKeySelective(manager);
		return manager;
	}

}
