package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.HmsStaffJobTitle;

@Repository
public interface HmsStaffJobTitleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HmsStaffJobTitle row);

    int insertSelective(HmsStaffJobTitle row);

    HmsStaffJobTitle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HmsStaffJobTitle row);

    int updateByPrimaryKey(HmsStaffJobTitle row);
}