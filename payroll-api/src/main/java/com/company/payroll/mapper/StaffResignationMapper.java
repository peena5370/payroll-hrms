package com.company.payroll.mapper;

import java.util.List;

import com.company.payroll.model.StaffResignation;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffResignationMapper {
    int deleteByPrimaryKey(Integer resignId);
    int insert(StaffResignation row);
    int insertSelective(StaffResignation row);
    List<StaffResignation> selectList();
    StaffResignation selectByPrimaryKey(Integer resignId);
    int updateByPrimaryKeySelective(StaffResignation row);
    int updateByPrimaryKey(StaffResignation row);
}