package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Department;

@Repository
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer deptno);

    int insert(Department row);

    int insertSelective(Department row);

    Department selectByPrimaryKey(Integer deptno);

    int updateByPrimaryKeySelective(Department row);

    int updateByPrimaryKey(Department row);
}