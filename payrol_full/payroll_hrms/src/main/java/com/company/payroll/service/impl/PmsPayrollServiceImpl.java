package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.PmsPayrollMapper;
import com.company.payroll.model.PmsPayroll;
import com.company.payroll.service.PmsPayrollService;
import com.company.payroll.util.SnowFlakeIdGenerator;

@Service
public class PmsPayrollServiceImpl implements PmsPayrollService {
	
	@Autowired
	private SnowFlakeIdGenerator snowFlakeIdGenerator;
	
	@Autowired
	private PmsPayrollMapper pmsPayrollMapper;

	@Override
	public int createPayroll(PmsPayroll payroll) {
		payroll.setId(snowFlakeIdGenerator.nextId());
		
		return pmsPayrollMapper.insertSelective(payroll);
	}

	@Override
	public int updatePayroll(PmsPayroll payroll) {
		return pmsPayrollMapper.updateByPrimaryKeySelective(payroll);
	}

	@Override
	public PmsPayroll getPayrollById(Long id) {
		return pmsPayrollMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PmsPayroll> getAllPayroll() {
		return null;
	}

	@Override
	public int deletePayrollById(Long id) {
		return pmsPayrollMapper.deleteByPrimaryKey(id);
	}

}
