package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.HmsStaffLearning;

@Repository
public interface HmsStaffLearningMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HmsStaffLearning row);

    int insertSelective(HmsStaffLearning row);

    HmsStaffLearning selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HmsStaffLearning row);

    int updateByPrimaryKey(HmsStaffLearning row);
}