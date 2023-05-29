package com.company.payroll.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.PayrollEmployeeMapper;
import com.company.payroll.mapper.PayrollManagerMapper;
import com.company.payroll.model.PayrollEmployee;
import com.company.payroll.model.PayrollManager;
import com.company.payroll.service.StaffPayrollService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StaffPayrollServiceImpl implements StaffPayrollService {

	@Autowired
	private PayrollEmployeeMapper payrollEmployeeMapper;
	
	@Autowired
	private PayrollManagerMapper payrollManagerMapper;
	
	@Override
	public Integer deletePayrollEmployee(int prid) {
		return payrollEmployeeMapper.deleteByPrimaryKey(prid);
	}

	@Override
	public Integer deletePayrollManager(int prMgrId) {
		return payrollManagerMapper.deleteByPrimaryKey(prMgrId);
	}

	@Override
	public Optional<List<PayrollEmployee>> findPayrollEmployeeByEId(int eid) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(payrollEmployeeMapper.selectListByEId(eid));
	}

	@Override
	public Optional<PayrollEmployee> findPayrollEmployeeById(int prid) {
		return Optional.ofNullable(payrollEmployeeMapper.selectByPrimaryKey(prid));
	}

	@Override
	public Optional<PayrollManager> findPayrollManagerById(int prMgrId) {
		return Optional.ofNullable(payrollManagerMapper.selectByPrimaryKey(prMgrId));
	}

	@Override
	public Optional<List<PayrollManager>> findPayrollManagerByMId(int mid) {
		return Optional.ofNullable(payrollManagerMapper.selectListByMId(mid));
	}

	@Override
	public PayrollEmployee insertPayrollEmployee(PayrollEmployee payroll) {
		payrollEmployeeMapper.insertSelective(payroll);
		return payroll;
	}

	@Override
	public PayrollManager insertPayrollManager(PayrollManager payrollManager) {
		payrollManagerMapper.insertSelective(payrollManager);
		return payrollManager;
	}

	@Override
	public PageInfo<PayrollEmployee> listPayrollEmployee(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<PayrollEmployee> list = payrollEmployeeMapper.selectList();
		return new PageInfo<PayrollEmployee>(list);
	}

	@Override
	public PageInfo<PayrollManager> listPayrollManager(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<PayrollManager> list = payrollManagerMapper.selectList();
		return new PageInfo<PayrollManager>(list);
	}

	@Override
	public PayrollEmployee updatePayrollEmployee(PayrollEmployee payroll) {
		payrollEmployeeMapper.updateByPrimaryKeySelective(payroll);
		return payroll;
	}

	@Override
	public PayrollManager updatePayrollManager(PayrollManager payrollManager) {
		payrollManagerMapper.updateByPrimaryKeySelective(payrollManager);
		return payrollManager;
	}

}
