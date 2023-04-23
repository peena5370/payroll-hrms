package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Salary;

@Repository
public interface SalaryMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Salary row);

    int insertSelective(Salary row);

    Salary selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(Salary row);

    int updateByPrimaryKey(Salary row);
}