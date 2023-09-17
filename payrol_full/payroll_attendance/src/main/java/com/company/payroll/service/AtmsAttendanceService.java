package com.company.payroll.service;

import java.time.LocalDate;

import com.company.payroll.model.AtmsStaffAttendance;
import com.github.pagehelper.PageInfo;

public interface AtmsAttendanceService {
	
	PageInfo<AtmsStaffAttendance> getAttendanceByStaffId(Long staffId, LocalDate startDate, LocalDate endDate, int page, int offset);
	
	Integer staffCheckIn(AtmsStaffAttendance staffAttendance);
	
	Integer attendanceUpdate(AtmsStaffAttendance staffAttendace);

}
