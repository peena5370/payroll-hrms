package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.LeaveMapper;
import com.company.payroll.model.Leave;
import com.company.payroll.service.LeaveService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveMapper leaveMapper;

	@Override
	public PageInfo<Leave> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Leave> list = leaveMapper.selectList();
		return new PageInfo<Leave>(list);
	}

	@Override
	public List<Leave> getListByEId(int eid) {
		return leaveMapper.selectListByEId(eid);
	}

	@Override
	public Leave getById(int lid) {
		return leaveMapper.selectByPrimaryKey(lid);
	}
	
	@Override
	public Integer insert(Leave leave) {
		return leaveMapper.insertSelective(leave);
	}

	@Override
	public Integer update(Leave leave) {
		return leaveMapper.updateByPrimaryKeySelective(leave);
	}

	@Override
	public Integer delete(int lid) {
		return leaveMapper.deleteByPrimaryKey(lid);
	}
}
