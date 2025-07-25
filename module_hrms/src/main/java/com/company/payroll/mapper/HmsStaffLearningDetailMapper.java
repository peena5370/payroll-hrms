package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.HmsStaffLearningDetail;

@Repository
public interface HmsStaffLearningDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HmsStaffLearningDetail row);

    int insertSelective(HmsStaffLearningDetail row);

    HmsStaffLearningDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HmsStaffLearningDetail row);

    int updateByPrimaryKeyWithBLOBs(HmsStaffLearningDetail row);

    int updateByPrimaryKey(HmsStaffLearningDetail row);
}