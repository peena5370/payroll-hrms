package com.company.payroll.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.HmsStaffBankingMapper;
import com.company.payroll.mapper.HmsStaffDetailMapper;
import com.company.payroll.mapper.HmsStaffJobTitleMapper;
import com.company.payroll.mapper.HmsStaffLeaveBalanceMapper;
import com.company.payroll.mapper.HmsStaffSalaryMapper;
import com.company.payroll.model.HmsStaffBanking;
import com.company.payroll.model.HmsStaffDetail;
import com.company.payroll.model.HmsStaffJobTitle;
import com.company.payroll.model.HmsStaffLeaveBalance;
import com.company.payroll.model.HmsStaffSalary;
import com.company.payroll.service.HmsStaffDetailService;
import com.github.pagehelper.PageInfo;

@Service
public class HmsStaffDetailServiceImpl implements HmsStaffDetailService {

	@Autowired
	private HmsStaffBankingMapper hmsStaffBankingMapper;
	
	@Autowired
	private HmsStaffDetailMapper hmsStaffDetailMapper;
	
	@Autowired
	private HmsStaffJobTitleMapper hmsStaffJobTitleMapper;
	
	@Autowired
	private HmsStaffLeaveBalanceMapper hmsStaffLeaveBalanceMapper;
	
	@Autowired
	private HmsStaffSalaryMapper hmsStaffSalaryMapper;
	@Override
	public PageInfo<HmsStaffBanking> getAllStaffbanking(int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<HmsStaffBanking> getStaffBankingById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertStaffBanking(HmsStaffBanking hmsStaffBanking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateStaffBanking(HmsStaffBanking hmsStaffBanking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteStaffBankingById(Long id) {
		return hmsStaffBankingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<HmsStaffDetail> getAllStaffDetail(int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<HmsStaffDetail> getStaffDetailById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<HmsStaffDetail> getStaffDetailByStaffId(Long staffId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertStaffDetail(HmsStaffDetail hmsStaffDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateStaffDetail(HmsStaffDetail hmsStaffDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteStaffDetailById(Long id) {
		return hmsStaffDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<HmsStaffJobTitle> getAllJobTitle(int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<HmsStaffJobTitle> getJobTitleById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertJobTitle(HmsStaffJobTitle hmsStaffJobTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateJobTitle(HmsStaffJobTitle hmsStaffJobTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteJobTitleById(Long id) {
		return hmsStaffJobTitleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Optional<HmsStaffLeaveBalance> getStaffLeaveBalanceById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertStaffLeaveBalance(HmsStaffLeaveBalance hmsStaffLeaveBalance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateStaffLeaveBalance(HmsStaffLeaveBalance hmsStaffLeaveBalance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteStaffLeaveBalanceById(Long id) {
		return hmsStaffLeaveBalanceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<HmsStaffSalary> getAllStaffSalary(int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<HmsStaffSalary> getStaffSalaryById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertStaffSalary(HmsStaffSalary hmsStaffSalary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateStaffSalary(HmsStaffSalary hmsStaffSalary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteStaffSalaryById(Long id) {
		return hmsStaffSalaryMapper.deleteByPrimaryKey(id);
	}

}
