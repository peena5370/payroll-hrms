package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.PayrollEmployee;

//@Repository
public interface PayrollEmployeeMapper {
    int deleteByPrimaryKey(Integer prId);

    int insert(PayrollEmployee row);

    int insertSelective(PayrollEmployee row);

    PayrollEmployee selectByPrimaryKey(Integer prId);

    int updateByPrimaryKeySelective(PayrollEmployee row);

    int updateByPrimaryKey(PayrollEmployee row);
}