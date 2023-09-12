package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.HmsStaffSalary;

@Repository
public interface HmsStaffSalaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HmsStaffSalary row);

    int insertSelective(HmsStaffSalary row);

    HmsStaffSalary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HmsStaffSalary row);

    int updateByPrimaryKey(HmsStaffSalary row);
}