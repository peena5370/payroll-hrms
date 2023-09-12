package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.PmsPayroll;

@Repository
public interface PmsPayrollMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsPayroll row);

    int insertSelective(PmsPayroll row);

    PmsPayroll selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsPayroll row);

    int updateByPrimaryKey(PmsPayroll row);
}