package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Employee;

@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer eId);

    int insert(Employee row);

    int insertSelective(Employee row);
    
    List<Employee> selectList();

    Employee selectByPrimaryKey(Integer eId);
    
    Employee selectByResignId(Integer resignId);

    int updateByPrimaryKeySelective(Employee row);

    int updateByPrimaryKey(Employee row);
}