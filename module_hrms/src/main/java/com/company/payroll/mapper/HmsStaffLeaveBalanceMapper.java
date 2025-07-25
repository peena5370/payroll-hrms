package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.HmsStaffLeaveBalance;

@Repository
public interface HmsStaffLeaveBalanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HmsStaffLeaveBalance row);

    int insertSelective(HmsStaffLeaveBalance row);

    HmsStaffLeaveBalance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HmsStaffLeaveBalance row);

    int updateByPrimaryKey(HmsStaffLeaveBalance row);
}