package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.HmsStaffLeaning;

@Repository
public interface HmsStaffLeaningMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HmsStaffLeaning row);

    int insertSelective(HmsStaffLeaning row);

    HmsStaffLeaning selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HmsStaffLeaning row);

    int updateByPrimaryKey(HmsStaffLeaning row);
}