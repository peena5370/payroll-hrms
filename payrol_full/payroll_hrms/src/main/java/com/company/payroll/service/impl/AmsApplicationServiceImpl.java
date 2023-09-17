package com.company.payroll.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.AmsLoanMapper;
import com.company.payroll.mapper.AmsResignationMapper;
import com.company.payroll.mapper.AmsTimeOffMapper;
import com.company.payroll.model.AmsLoan;
import com.company.payroll.model.AmsResignation;
import com.company.payroll.model.AmsTimeOff;
import com.company.payroll.service.AmsApplicationService;
import com.github.pagehelper.PageInfo;

@Service
public class AmsApplicationServiceImpl implements AmsApplicationService {
	
	@Autowired
	private AmsLoanMapper amsLoanMapper;

	@Autowired
	private AmsResignationMapper amsResignationMapper;
	
	@Autowired
	private AmsTimeOffMapper amsTimeOffMapper;
	
	@Override
	public PageInfo<AmsLoan> getAllLoan(int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<AmsLoan> getLoanById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertLoan(AmsLoan amsLoan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateLoan(AmsLoan amsLoan) {
		// TODO Auto-generated method stub
		return amsLoanMapper.updateByPrimaryKey(amsLoan);
	}

	@Override
	public Integer deleteLoanById(Long id) {
		return amsLoanMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<AmsResignation> getAllResignation(int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<AmsResignation> getResignationById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertResignation(AmsResignation amsResignation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateResignation(AmsResignation amsResignation) {
		return amsResignationMapper.updateByPrimaryKey(amsResignation);
	}

	@Override
	public Integer deleteResignationById(Long id) {
		return amsResignationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<AmsTimeOff> getAllTimeOff(int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<AmsTimeOff> getTimeOffById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertTimeOff(AmsTimeOff amsTimeOff) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateTimeOff(AmsTimeOff amsTimeOff) {
		return amsTimeOffMapper.updateByPrimaryKey(amsTimeOff);
	}

	@Override
	public Integer deleteTimeOffById(Long id) {
		return amsTimeOffMapper.deleteByPrimaryKey(id);
	}

}
