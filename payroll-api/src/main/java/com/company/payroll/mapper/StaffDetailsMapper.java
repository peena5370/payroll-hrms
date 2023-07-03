package com.company.payroll.mapper;

import java.util.List;

import com.company.payroll.model.StaffDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDetailsMapper {
    int countActiveDepartmentEmployee(Integer deptNo);
    int deleteByPrimaryKey(Integer eId);
    int insert(StaffDetails row);
    int insertSelective(StaffDetails row);
    StaffDetails selectByPrimaryKey(Integer eId);
    List<StaffDetails> selectList();
    int updateByPrimaryKey(StaffDetails row);
    int updateByPrimaryKeySelective(StaffDetails row);
    /**
     * @deprecated This method will be removed after this, since it's no more usable for the database.
     */
    StaffDetails selectByResignId(Integer resignId);
}