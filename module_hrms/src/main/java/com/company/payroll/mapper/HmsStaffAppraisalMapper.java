package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.HmsStaffAppraisal;

@Repository
public interface HmsStaffAppraisalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HmsStaffAppraisal row);

    int insertSelective(HmsStaffAppraisal row);

    HmsStaffAppraisal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HmsStaffAppraisal row);

    int updateByPrimaryKey(HmsStaffAppraisal row);
}