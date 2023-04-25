package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Salary;

@Repository
public interface SalaryMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Salary row);

    int insertSelective(Salary row);
    
    List<Salary> selectList();

    Salary selectByPrimaryKey(Integer sId);

    int updateByPrimaryKeySelective(Salary row);

    int updateByPrimaryKey(Salary row);
}