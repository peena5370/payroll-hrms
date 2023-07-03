package com.company.payroll.mapper;

import java.util.List;

import com.company.payroll.model.StaffPayroll;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffPayrollMapper {
    int deleteByPrimaryKey(Integer prId);
    int insert(StaffPayroll row);
    int insertSelective(StaffPayroll row);
    List<StaffPayroll> selectList();
    List<StaffPayroll> selectListByStaffId(Integer staffId);
    StaffPayroll selectByPrimaryKey(Integer prId);
    int updateByPrimaryKeySelective(StaffPayroll row);
    int updateByPrimaryKey(StaffPayroll row);
}