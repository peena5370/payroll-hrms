package com.company.payroll.service.impl;

import java.util.List;

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
	public PageInfo<Salary> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Salary> list = salaryMapper.selectList();
		return new PageInfo<Salary>(list);
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
