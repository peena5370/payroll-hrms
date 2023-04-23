package com.company.payroll.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.EmployeeMapper;
import com.company.payroll.model.Employee;
import com.company.payroll.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public List<Employee> listEmployee() {
		// TODO Auto-generated method stub
		return employeeMapper.selectEmployeeList();
	}

	@Override
	public Employee getEmployeeById(int eid) {
		// TODO Auto-generated method stub
		return employeeMapper.selectEmployeebyId(eid);
	}

	@Override
	public Employee getEmployeeBySapId(int esapid) {
		// TODO Auto-generated method stub
		return employeeMapper.selectEmployeebySapId(esapid);
	}

	@Override
	public Employee getEmployeePasswordBySapId(int esapid) {
		// TODO Auto-generated method stub
		return employeeMapper.selectEmployeePasswordbySapId(esapid);
	}

	@Override
	public Integer countEmployee() {
		// TODO Auto-generated method stub
		return employeeMapper.countEmployee();
	}

	@Override
	public Integer countAvailableEmployee() {
		// TODO Auto-generated method stub
		return employeeMapper.countAvailableEmployee();
	}

	@Override
	public Integer getEmployeeSapId() {
		// TODO Auto-generated method stub
		return employeeMapper.selectEmployeeSapId();
	}

	@Override
	public Integer insertEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return employeeMapper.insertEmployee(emp);
	}

	@Override
	public Integer updateInfoByManager(Employee emp) {
		// TODO Auto-generated method stub
		return employeeMapper.updateEmployeebyManager(emp);
	}

	@Override
	public Integer updateInfoByEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return employeeMapper.updateEmployeebyEmployee(emp);
	}

	@Override
	public Integer updatePasswordBySapId(Employee emp) {
		// TODO Auto-generated method stub
		return employeeMapper.updateEmployeePasswordbySapId(emp);
	}

	@Override
	public Integer updateResignDateBySapId(int sapid, LocalDate dateresign) {
		// TODO Auto-generated method stub
		return employeeMapper.updateEmployeeResignDatebySapId(sapid, dateresign);
	}

	@Override
	public Integer deleteEmployee(int eid) {
		// TODO Auto-generated method stub
		return employeeMapper.deleteEmployee(eid);
	}

}
