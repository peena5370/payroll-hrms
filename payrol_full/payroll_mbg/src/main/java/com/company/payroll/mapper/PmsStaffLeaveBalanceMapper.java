package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.PmsStaffLeaveBalance;

@Repository
public interface PmsStaffLeaveBalanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsStaffLeaveBalance row);

    int insertSelective(PmsStaffLeaveBalance row);

    PmsStaffLeaveBalance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsStaffLeaveBalance row);

    int updateByPrimaryKey(PmsStaffLeaveBalance row);
}