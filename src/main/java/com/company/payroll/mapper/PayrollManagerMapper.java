package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.PayrollManager;

@Repository
public interface PayrollManagerMapper {
    int deleteByPrimaryKey(Integer prMgrId);

    int insert(PayrollManager row);

    int insertSelective(PayrollManager row);

    PayrollManager selectByPrimaryKey(Integer prMgrId);

    int updateByPrimaryKeySelective(PayrollManager row);

    int updateByPrimaryKey(PayrollManager row);
}