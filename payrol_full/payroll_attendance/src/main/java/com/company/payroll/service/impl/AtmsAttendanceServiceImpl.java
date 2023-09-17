package com.company.payroll.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.AtmsStaffAttendanceMapper;
import com.company.payroll.model.AtmsStaffAttendance;
import com.company.payroll.service.AtmsAttendanceService;
import com.github.pagehelper.PageInfo;

@Service
public class AtmsAttendanceServiceImpl implements AtmsAttendanceService {
	
	@Autowired
	private AtmsStaffAttendanceMapper atmsStaffAttendanceMapper;

	@Override
	public PageInfo<AtmsStaffAttendance> getAttendanceByStaffId(Long staffId, LocalDate startDate, LocalDate endDate,
			int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer staffCheckIn(AtmsStaffAttendance staffAttendance) {
		return atmsStaffAttendanceMapper.insertSelective(staffAttendance);
	}

	@Override
	public Integer attendanceUpdate(AtmsStaffAttendance staffAttendace) {
		return null;
	}
	
}
