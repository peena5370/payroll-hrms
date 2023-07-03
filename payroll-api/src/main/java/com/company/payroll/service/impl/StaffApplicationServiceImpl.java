package com.company.payroll.service.impl;

import java.util.List;
import java.util.Optional;

import com.company.payroll.model.StaffLeave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.StaffLeaveMapper;
import com.company.payroll.mapper.StaffLoanMapper;
import com.company.payroll.model.StaffLoan;
import com.company.payroll.service.StaffApplicationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StaffApplicationServiceImpl implements StaffApplicationService {

	@Autowired
	private StaffLeaveMapper staffLeaveMapper;
	
	@Autowired
	private StaffLoanMapper staffLoanMapper;
	
	@Override
	public Integer deleteLeave(Integer lId) {
		return staffLeaveMapper.deleteByPrimaryKey(lId);
	}

	@Override
	public Integer deleteLoan(Integer loanId) {
		return staffLoanMapper.deleteByPrimaryKey(loanId);
	}

	@Override
	public Optional<List<StaffLeave>> findLeaveByEId(int eid) {
		return Optional.empty();
	}

	@Override
	public Optional<List<StaffLeave>> findLeaveByStaffId(Integer staffId) {
		return Optional.ofNullable(staffLeaveMapper.selectListByStaffId(staffId));
	}

	@Override
	public Optional<StaffLeave> findLeaveById(Integer lId) {
		return Optional.ofNullable(staffLeaveMapper.selectByPrimaryKey(lId));
	}

	@Override
	public Optional<List<StaffLoan>> findLoanByEId(int eid) {
		return Optional.empty();
	}

	@Override
	public Optional<List<StaffLoan>> findLoanByStaffId(Integer staffId) {
		return Optional.ofNullable(staffLoanMapper.selectListByStaffId(staffId));
	}

	@Override
	public Optional<StaffLoan> findLoanById(Integer loanId) {
		return Optional.ofNullable(staffLoanMapper.selectByPrimaryKey(loanId));
	}

	@Override
	public StaffLeave insertLeave(StaffLeave staffLeave) {
		staffLeaveMapper.insertSelective(staffLeave);
		return staffLeave;
	}

	@Override
	public StaffLoan insertLoan(StaffLoan staffLoan) {
		staffLoanMapper.insertSelective(staffLoan);
		return staffLoan;
	}

	@Override
	public PageInfo<StaffLeave> listLeave(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<StaffLeave>(staffLeaveMapper.selectList());
	}

	@Override
	public PageInfo<StaffLoan> listLoan(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<StaffLoan>(staffLoanMapper.selectList());
	}

	@Override
	public StaffLeave updateLeave(StaffLeave staffLeave) {
		staffLeaveMapper.updateByPrimaryKeySelective(staffLeave);
		return staffLeave;
	}

	@Override
	public StaffLoan updateLoan(StaffLoan staffLoan) {
		staffLoanMapper.updateByPrimaryKeySelective(staffLoan);
		return staffLoan;
	}

}
