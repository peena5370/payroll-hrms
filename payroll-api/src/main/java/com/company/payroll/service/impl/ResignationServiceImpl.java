package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.ResignationMapper;
import com.company.payroll.model.Resignation;
import com.company.payroll.service.ResignationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ResignationServiceImpl implements ResignationService {

	@Autowired
	private ResignationMapper resignationMapper;
	
	@Override
	public PageInfo<Resignation> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Resignation> list = resignationMapper.selectList();
		return new PageInfo<Resignation>(list);
	}

	@Override
	public Resignation getById(int resignid) {
		return resignationMapper.selectByPrimaryKey(resignid);
	}

	@Override
	public Integer insert(Resignation resign) {
		return resignationMapper.insertSelective(resign);
	}

	@Override
	public Integer update(Resignation resign) {
		return resignationMapper.updateByPrimaryKeySelective(resign);
	}

	@Override
	public Integer delete(int resignid) {
		return resignationMapper.deleteByPrimaryKey(resignid);
	}
}
