package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.AmsStaffAttendance;

@Repository
public interface AmsStaffAttendanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AmsStaffAttendance row);

    int insertSelective(AmsStaffAttendance row);

    AmsStaffAttendance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AmsStaffAttendance row);

    int updateByPrimaryKey(AmsStaffAttendance row);
}