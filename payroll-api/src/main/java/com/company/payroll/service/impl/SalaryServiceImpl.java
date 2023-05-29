package com.company.payroll.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.SalaryMapper;
import com.company.payroll.model.Salary;
import com.company.payroll.service.SalaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	private SalaryMapper salaryMapper;
	
	@Override
	public PageInfo<Salary> list(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Salary> list = salaryMapper.selectList();
		return new PageInfo<Salary>(list);
	}

	@Override
	public Optional<Salary> findById(int sid) {
		return Optional.ofNullable(salaryMapper.selectByPrimaryKey(sid));
	}

	@Override
	public Salary update(Salary salary) {
		salaryMapper.updateByPrimaryKeySelective(salary);
		return salary;
	}
}
