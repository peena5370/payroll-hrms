package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.LoanMapper;
import com.company.payroll.model.Loan;
import com.company.payroll.service.LoanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanMapper loanMapper;
	
	@Override
	public PageInfo<Loan> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Loan> list = loanMapper.selectList();
		return new PageInfo<Loan>(list);
	}

	@Override
	public List<Loan> getListByEId(int eid) {
		return loanMapper.selectListByEId(eid);
	}

	@Override
	public Loan getById(int lid) {
		return loanMapper.selectByPrimaryKey(lid);
	}
	
	@Override
	public Integer insert(Loan loan) {
		return loanMapper.insertSelective(loan);
	}

	@Override
	public Integer update(Loan loan) {
		return loanMapper.updateByPrimaryKeySelective(loan);
	}

	@Override
	public Integer delete(int lid) {
		return loanMapper.deleteByPrimaryKey(lid);
	}
}
