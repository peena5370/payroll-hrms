package com.company.payroll.mapper;

import java.util.List;

import com.company.payroll.model.StaffLeave;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffLeaveMapper {
    int deleteByPrimaryKey(Integer lId);
    int insert(StaffLeave row);
    int insertSelective(StaffLeave row);
    List<StaffLeave> selectList();
    List<StaffLeave> selectListByStaffId(Integer staffId);
    StaffLeave selectByPrimaryKey(Integer lId);
    int updateByPrimaryKeySelective(StaffLeave row);
    int updateByPrimaryKey(StaffLeave row);
}