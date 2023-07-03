package com.company.payroll.mapper;

import java.util.List;

import com.company.payroll.model.StaffSalary;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffSalaryMapper {
    int deleteByPrimaryKey(Integer sId);
    int insert(StaffSalary row);
    int insertSelective(StaffSalary row);
    List<StaffSalary> selectList();
    StaffSalary selectByPrimaryKey(Integer sId);
    int updateByPrimaryKeySelective(StaffSalary row);
    int updateByPrimaryKey(StaffSalary row);
}