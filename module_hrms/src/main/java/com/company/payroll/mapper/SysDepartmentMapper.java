package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.SysDepartment;

@Repository
public interface SysDepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDepartment row);

    int insertSelective(SysDepartment row);

    SysDepartment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDepartment row);

    int updateByPrimaryKey(SysDepartment row);
}