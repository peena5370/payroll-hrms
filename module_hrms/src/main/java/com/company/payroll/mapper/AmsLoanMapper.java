package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.AmsLoan;

@Repository
public interface AmsLoanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AmsLoan row);

    int insertSelective(AmsLoan row);

    AmsLoan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AmsLoan row);

    int updateByPrimaryKey(AmsLoan row);
}