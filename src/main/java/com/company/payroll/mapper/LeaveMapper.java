package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Leave;

@Repository
public interface LeaveMapper {
    int deleteByPrimaryKey(Integer lId);

    int insert(Leave row);

    int insertSelective(Leave row);
    
    List<Leave> selectList();
    
    List<Leave> selectListByEId(int eid);
    
    Leave selectByPrimaryKey(Integer lId);

    int updateByPrimaryKeySelective(Leave row);

    int updateByPrimaryKey(Leave row);
}