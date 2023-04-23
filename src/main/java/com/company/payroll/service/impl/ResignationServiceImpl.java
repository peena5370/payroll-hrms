package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.ResignationMapper;
import com.company.payroll.model.Resignation;
import com.company.payroll.service.ResignationService;

@Service
public class ResignationServiceImpl implements ResignationService {

	@Autowired
	private ResignationMapper resignationMapper;
	
	@Override
	public List<Resignation> listResignation() {
		// TODO Auto-generated method stub
		return resignationMapper.selectResignList();
	}

	@Override
	public Resignation getInfoById(int resignid) {
		// TODO Auto-generated method stub
		return resignationMapper.selectResignbyId(resignid);
	}

	@Override
	public Integer insertResignInfo(Resignation resign) {
		// TODO Auto-generated method stub
		return resignationMapper.insertResign(resign);
	}

	@Override
	public Integer updateResignInfoById(Resignation resign) {
		// TODO Auto-generated method stub
		return resignationMapper.updateResignbyId(resign);
	}

	@Override
	public Integer updateResignStatus(Resignation resign) {
		// TODO Auto-generated method stub
		return resignationMapper.updateResignStatusbyManagerId(resign);
	}

	@Override
	public Integer deleteResign(int resignid) {
		// TODO Auto-generated method stub
		return resignationMapper.deleteResign(resignid);
	}

}
