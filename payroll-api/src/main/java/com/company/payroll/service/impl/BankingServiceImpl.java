package com.company.payroll.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.BankingInfoMapper;
import com.company.payroll.model.BankingInfo;
import com.company.payroll.service.BankingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class BankingServiceImpl implements BankingService {

	@Autowired
	private BankingInfoMapper bankingInfoMapper;
	
	@Override
	public Optional<BankingInfo> findById(int bid) {
		return Optional.ofNullable(bankingInfoMapper.selectByPrimaryKey(bid));
	}

	@Override
	public PageInfo<BankingInfo> list(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<BankingInfo> list = bankingInfoMapper.selectList();
		return new PageInfo<BankingInfo>(list);
	}

	@Override
	public BankingInfo update(BankingInfo bankingInfo) {
		bankingInfoMapper.updateByPrimaryKey(bankingInfo);
		return bankingInfo;
	}
}
