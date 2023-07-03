package com.company.payroll.service.impl;

import com.company.payroll.mapper.StaffAttendanceMapper;
import com.company.payroll.model.StaffAttendance;
import com.company.payroll.service.StaffAttendanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StaffAttendanceServiceImpl implements StaffAttendanceService {

    @Autowired
    private StaffAttendanceMapper staffAttendanceMapper;

    @Override
    public PageInfo<StaffAttendance> getStaffAttendance(Integer staffId, LocalDate startDate, LocalDate endDate, int page, int offset) {
        PageHelper.startPage(page, offset);
        return new PageInfo<StaffAttendance>(staffAttendanceMapper.selectByStaffIdAndDate(staffId, startDate, endDate));
    }

    @Override
    public Integer staffCheckIn(StaffAttendance staffAttendance) {
        return staffAttendanceMapper.insert(staffAttendance);
    }

    @Override
    public Integer attendanceUpdate(StaffAttendance staffAttendance) {
        return staffAttendanceMapper.updateCheckTime(staffAttendance);
    }
}
