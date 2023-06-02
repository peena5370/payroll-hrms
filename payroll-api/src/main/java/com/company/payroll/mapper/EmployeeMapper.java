package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Employee;

@Repository
public interface EmployeeMapper {
    int countActiveDepartmentEmployee(Integer deptno);

    int deleteByPrimaryKey(Integer eId);

    int insert(Employee row);
    
    int insertSelective(Employee row);
    
    Employee selectByPrimaryKey(Integer eId);

    Employee selectByResignId(Integer resignId);
    
    List<Employee> selectList();

    int updateByPrimaryKey(Employee row);

    int updateByPrimaryKeySelective(Employee row);
}