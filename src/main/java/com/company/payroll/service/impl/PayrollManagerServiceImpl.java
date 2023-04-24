package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.payroll.mapper.PayrollManagerMapper;
import com.company.payroll.model.PayrollManager;
import com.company.payroll.service.PayrollManagerService;

@Service
public class PayrollManagerServiceImpl implements PayrollManagerService {

	private PayrollManagerMapper payrollManagerMapper;
	
	public PayrollManagerServiceImpl(PayrollManagerMapper payrollManagerMapper) {
		this.payrollManagerMapper = payrollManagerMapper;
	}
	
	@Override
	public List<PayrollManager> getList() {
		return payrollManagerMapper.selectList();
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
