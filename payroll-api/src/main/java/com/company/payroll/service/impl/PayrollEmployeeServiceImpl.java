package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.PayrollEmployeeMapper;
import com.company.payroll.model.PayrollEmployee;
import com.company.payroll.service.PayrollEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PayrollEmployeeServiceImpl implements PayrollEmployeeService {

	@Autowired
	private PayrollEmployeeMapper payrollEmployeeMapper;
	
	@Override
	public PageInfo<PayrollEmployee> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<PayrollEmployee> list = payrollEmployeeMapper.selectList();
		return new PageInfo<PayrollEmployee>(list);
	}

	@Override
	public List<PayrollEmployee> getListByEId(int eid) {
		return payrollEmployeeMapper.selectListByEId(eid);
	}
	
	@Override
	public PayrollEmployee getById(int prid) {
		return payrollEmployeeMapper.selectByPrimaryKey(prid);
	}
	@Override
	public Integer insert(PayrollEmployee payroll) {
		return payrollEmployeeMapper.insertSelective(payroll);
	}

	@Override
	public Integer update(PayrollEmployee payroll) {
		return payrollEmployeeMapper.updateByPrimaryKeySelective(payroll);
	}

	@Override
	public Integer delete(int prid) {
		return payrollEmployeeMapper.deleteByPrimaryKey(prid);
	}
}
