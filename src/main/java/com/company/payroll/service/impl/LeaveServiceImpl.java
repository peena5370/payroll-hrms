package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.LeaveMapper;
import com.company.payroll.model.Leave;
import com.company.payroll.service.LeaveService;

//@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveMapper leaveMapper;
	
	@Override
	public List<Leave> listLeave() {
		// TODO Auto-generated method stub
		return leaveMapper.selectLeave();
	}

	@Override
	public List<Leave> listLeaveBySapId(int esapid) {
		// TODO Auto-generated method stub
		return leaveMapper.selectLeavebySapId(esapid);
	}

	@Override
	public Integer insertLeave(Leave leave) {
		// TODO Auto-generated method stub
		return leaveMapper.insertLeave(leave);
	}

	@Override
	public Integer updateLeave(Leave leave) {
		// TODO Auto-generated method stub
		return leaveMapper.updateLeave(leave);
	}

}
