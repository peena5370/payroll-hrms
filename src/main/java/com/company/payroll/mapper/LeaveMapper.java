package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Leave;

//@Repository
public interface LeaveMapper {
    int deleteByPrimaryKey(Integer lId);

    int insert(Leave row);

    int insertSelective(Leave row);

    Leave selectByPrimaryKey(Integer lId);

    int updateByPrimaryKeySelective(Leave row);

    int updateByPrimaryKey(Leave row);
}