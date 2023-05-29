package com.company.payroll.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.LeaveMapper;
import com.company.payroll.mapper.LoanMapper;
import com.company.payroll.model.Leave;
import com.company.payroll.model.Loan;
import com.company.payroll.service.StaffApplicationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StaffApplicationServiceImpl implements StaffApplicationService {

	@Autowired
	private LeaveMapper leaveMapper;
	
	@Autowired
	private LoanMapper loanMapper;
	
	@Override
	public Integer deleteLeave(int lid) {
		return leaveMapper.deleteByPrimaryKey(lid);
	}

	@Override
	public Integer deleteLoan(int lid) {
		return loanMapper.deleteByPrimaryKey(lid);
	}

	@Override
	public Optional<List<Leave>> findLeaveByEId(int eid) {
		return Optional.ofNullable(leaveMapper.selectListByEId(eid));
	}

	@Override
	public Optional<Leave> findLeaveById(int lid) {
		return Optional.ofNullable(leaveMapper.selectByPrimaryKey(lid));
	}

	@Override
	public Optional<List<Loan>> findLoanByEId(int eid) {
		return Optional.ofNullable(loanMapper.selectListByEId(eid));
	}

	@Override
	public Optional<Loan> findLoanById(int lid) {
		return Optional.ofNullable(loanMapper.selectByPrimaryKey(lid));
	}

	@Override
	public Leave insertLeave(Leave leave) {
		leaveMapper.insertSelective(leave);
		return leave;
	}

	@Override
	public Loan insertLoan(Loan loan) {
		loanMapper.insertSelective(loan);
		return loan;
	}

	@Override
	public PageInfo<Leave> listLeave(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Leave> list = leaveMapper.selectList();
		return new PageInfo<Leave>(list);
	}

	@Override
	public PageInfo<Loan> listLoan(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Loan> list = loanMapper.selectList();
		return new PageInfo<Loan>(list);
	}

	@Override
	public Leave updateLeave(Leave leave) {
		leaveMapper.updateByPrimaryKeySelective(leave);
		return leave;
	}

	@Override
	public Loan updateLoan(Loan loan) {
		loanMapper.updateByPrimaryKeySelective(loan);
		return loan;
	}

}
