package com.company.payroll.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.PmsPayrollMapper;
import com.company.payroll.model.PmsPayroll;
import com.company.payroll.service.PmsPayrollService;
import com.github.pagehelper.PageInfo;

@Service
public class PmsPayrollServiceImpl implements PmsPayrollService {
	
	@Autowired
	private PmsPayrollMapper pmsPayrollMapper;

	@Override
	public PageInfo<PmsPayroll> getAllPayroll(int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<PmsPayroll> getAllPayrollByStaffId(Long staffId, int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PmsPayroll> getPayrollById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertPayroll(PmsPayroll pmsPayroll) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updatePayroll(PmsPayroll pmsPayroll) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deletePayrollById(Long id) {
		return pmsPayrollMapper.deleteByPrimaryKey(id);
	}

}
