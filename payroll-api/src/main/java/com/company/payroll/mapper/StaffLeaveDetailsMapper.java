package com.company.payroll.mapper;

import com.company.payroll.model.StaffLeaveDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffLeaveDetailsMapper {
    int deleteByPrimaryKey(Integer ldId);
    int insert(StaffLeaveDetails row);
    int insertSelective(StaffLeaveDetails row);
    StaffLeaveDetails selectByPrimaryKey(Integer ldId);
    List<StaffLeaveDetails> selectList();
    int updateByPrimaryKeySelective(StaffLeaveDetails row);
    int updateByPrimaryKey(StaffLeaveDetails row);
}
