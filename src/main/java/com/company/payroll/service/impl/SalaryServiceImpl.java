package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.SalaryMapper;
import com.company.payroll.model.Salary;
import com.company.payroll.service.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	private SalaryMapper salaryMapper;
	
	@Override
	public List<Salary> listSalary() {
		// TODO Auto-generated method stub
		return salaryMapper.selectSalaryList();
	}

	@Override
	public Salary getInfoBySapId(int esapid) {
		// TODO Auto-generated method stub
		return salaryMapper.selectSalarybySapId(esapid);
	}

	@Override
	public Integer insertSalary(Salary salary) {
		// TODO Auto-generated method stub
		return salaryMapper.insertSalary(salary);
	}

	@Override
	public Integer updateSalary(Salary salary) {
		// TODO Auto-generated method stub
		return salaryMapper.updateSalary(salary);
	}

}
