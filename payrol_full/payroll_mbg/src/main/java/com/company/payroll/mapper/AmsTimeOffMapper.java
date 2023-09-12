package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.AmsTimeOff;

@Repository
public interface AmsTimeOffMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AmsTimeOff row);

    int insertSelective(AmsTimeOff row);

    AmsTimeOff selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AmsTimeOff row);

    int updateByPrimaryKey(AmsTimeOff row);
}