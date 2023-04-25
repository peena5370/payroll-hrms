package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Department;

@Repository
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer deptno);

    int insert(Department row);

    int insertSelective(Department row);
    
    List<Department> selectList();

    Department selectByPrimaryKey(Integer deptno);

    int updateByPrimaryKeySelective(Department row);

    int updateByPrimaryKey(Department row);
}