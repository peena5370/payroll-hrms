package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.LoanMapper;
import com.company.payroll.model.Loan;
import com.company.payroll.service.LoanService;

//@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanMapper loanMapper;
	
	@Override
	public List<Loan> listLoan() {
		// TODO Auto-generated method stub
		return loanMapper.selectLoan();
	}

	@Override
	public List<Loan> listLoanBySapId(int esapid) {
		// TODO Auto-generated method stub
		return loanMapper.selectLoanbySapId(esapid);
	}

	@Override
	public Integer insertLoan(Loan loan) {
		// TODO Auto-generated method stub
		return loanMapper.insertLoan(loan);
	}

	@Override
	public Integer updateLoan(Loan loan) {
		// TODO Auto-generated method stub
		return loanMapper.updateLoan(loan);
	}

}
