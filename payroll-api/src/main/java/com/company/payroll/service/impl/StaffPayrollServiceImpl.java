package com.company.payroll.service.impl;

import java.util.List;
import java.util.Optional;

import com.company.payroll.model.StaffPayroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.StaffPayrollMapper;
import com.company.payroll.mapper.PayrollManagerMapper;
import com.company.payroll.model.PayrollManager;
import com.company.payroll.service.StaffPayrollService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StaffPayrollServiceImpl implements StaffPayrollService {

	@Autowired
	private StaffPayrollMapper staffPayrollMapper;

	
	@Override
	public Integer deletePayrollEmployee(int prid) {
		return null;
	}

	@Override
	public Integer deletePayrollManager(int prMgrId) {
		return null;
	}

	@Override
	public Integer deleteStaffPayroll(Integer prId) {
		return staffPayrollMapper.deleteByPrimaryKey(prId);
	}

	@Override
	public Optional<List<StaffPayroll>> findPayrollEmployeeByEId(int eid) {
		return Optional.empty();
	}

	@Override
	public Optional<StaffPayroll> findPayrollEmployeeById(int prid) {
		return Optional.empty();
	}

	@Override
	public Optional<PayrollManager> findPayrollManagerById(int prMgrId) {
		return Optional.empty();
	}

	@Override
	public Optional<StaffPayroll> findStaffPayrollById(Integer prId) {
		return Optional.ofNullable(staffPayrollMapper.selectByPrimaryKey(prId));
	}

	@Override
	public Optional<List<PayrollManager>> findPayrollManagerByMId(int mid) {
		return Optional.empty();
	}

	@Override
	public List<StaffPayroll> findStaffPayrollByStaffId(Integer staffId) {
		return staffPayrollMapper.selectListByStaffId(staffId);
	}

	@Override
	public StaffPayroll insertPayrollEmployee(StaffPayroll payroll) {
		return null;
	}

	@Override
	public PayrollManager insertPayrollManager(PayrollManager payrollManager) {
		return null;
	}

	@Override
	public StaffPayroll insertStaffPayroll(StaffPayroll staffPayroll) {
		staffPayrollMapper.insertSelective(staffPayroll);
		return staffPayroll;
	}

	@Override
	public PageInfo<StaffPayroll> listPayrollEmployee(int page, int offset) {
		return null;
	}

	@Override
	public PageInfo<PayrollManager> listPayrollManager(int page, int offset) {
		return null;
	}

	@Override
	public PageInfo<StaffPayroll> listStaffPayroll(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<StaffPayroll>(staffPayrollMapper.selectList());
	}

	@Override
	public StaffPayroll updatePayrollEmployee(StaffPayroll payroll) {
		return null;
	}

	@Override
	public PayrollManager updatePayrollManager(PayrollManager payrollManager) {
		return null;
	}

	@Override
	public StaffPayroll updateStaffPayroll(StaffPayroll staffPayroll) {
		staffPayrollMapper.updateByPrimaryKeySelective(staffPayroll);
		return staffPayroll;
	}

}
