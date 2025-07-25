package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.SysProfile;

@Repository
public interface SysProfileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysProfile row);

    int insertSelective(SysProfile row);

    SysProfile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysProfile row);

    int updateByPrimaryKey(SysProfile row);
}