package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Employee;

@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer eId);

    int insert(Employee row);

    int insertSelective(Employee row);

    Employee selectByPrimaryKey(Integer eId);

    int updateByPrimaryKeySelective(Employee row);

    int updateByPrimaryKey(Employee row);
}