package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.payroll.mapper.LeaveMapper;
import com.company.payroll.model.Leave;
import com.company.payroll.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

	private LeaveMapper leaveMapper;
	
	public LeaveServiceImpl(LeaveMapper leaveMapper) {
		this.leaveMapper = leaveMapper;
	}
	
	@Override
	public List<Leave> getList() {
		return leaveMapper.selectList();
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
