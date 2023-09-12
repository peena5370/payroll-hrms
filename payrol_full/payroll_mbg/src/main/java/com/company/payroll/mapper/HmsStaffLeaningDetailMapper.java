package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.HmsStaffLeaningDetail;

@Repository
public interface HmsStaffLeaningDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HmsStaffLeaningDetail row);

    int insertSelective(HmsStaffLeaningDetail row);

    HmsStaffLeaningDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HmsStaffLeaningDetail row);

    int updateByPrimaryKeyWithBLOBs(HmsStaffLeaningDetail row);

    int updateByPrimaryKey(HmsStaffLeaningDetail row);
}