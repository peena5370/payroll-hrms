package com.company.payroll.service.impl;

import java.util.List;
import java.util.Optional;

import com.company.payroll.model.StaffSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.StaffSalaryMapper;
import com.company.payroll.service.StaffSalaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StaffSalaryServiceImpl implements StaffSalaryService {

	@Autowired
	private StaffSalaryMapper staffSalaryMapper;
	
	@Override
	public PageInfo<StaffSalary> list(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<StaffSalary>(staffSalaryMapper.selectList());
	}

	@Override
	public Optional<StaffSalary> findById(Integer sId) {
		return Optional.ofNullable(staffSalaryMapper.selectByPrimaryKey(sId));
	}

	@Override
	public StaffSalary update(StaffSalary staffSalary) {
		staffSalaryMapper.updateByPrimaryKeySelective(staffSalary);
		return staffSalary;
	}
}
