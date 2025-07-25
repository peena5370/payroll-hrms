package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.AtmsStaffAttendance;

@Repository
public interface AtmsStaffAttendanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AtmsStaffAttendance row);

    int insertSelective(AtmsStaffAttendance row);

    AtmsStaffAttendance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AtmsStaffAttendance row);

    int updateByPrimaryKey(AtmsStaffAttendance row);
}