package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.payroll.mapper.SalaryMapper;
import com.company.payroll.model.Salary;
import com.company.payroll.service.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService {

	private SalaryMapper salaryMapper;
	
	public SalaryServiceImpl(SalaryMapper salaryMapper) {
		this.salaryMapper = salaryMapper;
	}
	
	@Override
	public List<Salary> getList() {
		return salaryMapper.selectList();
	}

	@Override
	public Salary getById(int sid) {
		return salaryMapper.selectByPrimaryKey(sid);
	}

	@Override
	public Integer insert(Salary salary) {
		return salaryMapper.insertSelective(salary);
	}

	@Override
	public Integer update(Salary salary) {
		return salaryMapper.updateByPrimaryKeySelective(salary);
	}

	@Override
	public Integer delete(int sid) {
		return salaryMapper.deleteByPrimaryKey(sid);
	}
}
