package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.HmsStaffDetail;

@Repository
public interface HmsStaffDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HmsStaffDetail row);

    int insertSelective(HmsStaffDetail row);

    HmsStaffDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HmsStaffDetail row);

    int updateByPrimaryKey(HmsStaffDetail row);
}