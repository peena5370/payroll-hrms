package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.SysAccount;

@Repository
public interface SysAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysAccount row);

    int insertSelective(SysAccount row);

    SysAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAccount row);

    int updateByPrimaryKey(SysAccount row);
}