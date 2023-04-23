package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.BankingMapper;
import com.company.payroll.model.Banking;
import com.company.payroll.service.BankingService;

@Service
public class BankingServiceImpl implements BankingService {

	@Autowired
	private BankingMapper bankingMapper;
	
	@Override
	public List<Banking> listBankInfo() {
		// TODO Auto-generated method stub
		return bankingMapper.selectBankInfo();
	}

	@Override
	public Banking getBankInfoById(int bid) {
		// TODO Auto-generated method stub
		return bankingMapper.selectBankInfobyId(bid);
	}

	@Override
	public Integer insertBankInfo(Banking bank) {
		// TODO Auto-generated method stub
		return bankingMapper.insertBankInfo(bank);
	}

	@Override
	public Integer updateBankInfoById(Banking bank) {
		// TODO Auto-generated method stub
		return bankingMapper.updateBankInfobyId(bank);
	}

	@Override
	public Integer deleteBankInfo(int bid) {
		// TODO Auto-generated method stub
		return bankingMapper.deleteBankInfo(bid);
	}

}
