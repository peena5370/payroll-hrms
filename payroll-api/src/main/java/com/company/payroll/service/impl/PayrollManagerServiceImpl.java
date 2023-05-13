package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.PayrollManagerMapper;
import com.company.payroll.model.PayrollManager;
import com.company.payroll.service.PayrollManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PayrollManagerServiceImpl implements PayrollManagerService {

	@Autowired
	private PayrollManagerMapper payrollManagerMapper;

	@Override
	public PageInfo<PayrollManager> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<PayrollManager> list = payrollManagerMapper.selectList();
		return new PageInfo<PayrollManager>(list);
	}

	@Override
	public List<PayrollManager> getListByMId(int mid) {
		return payrollManagerMapper.selectListByMId(mid);
	}

	@Override
	public PayrollManager getById(int prMgrId) {
		return payrollManagerMapper.selectByPrimaryKey(prMgrId);
	}

	@Override
	public Integer insert(PayrollManager payrollManager) {
		return payrollManagerMapper.insertSelective(payrollManager);
	}

	@Override
	public Integer update(PayrollManager payrollManager) {
		return payrollManagerMapper.updateByPrimaryKeySelective(payrollManager);
	}

	@Override
	public Integer delete(int prMgrId) {
		return payrollManagerMapper.deleteByPrimaryKey(prMgrId);
	}
}
