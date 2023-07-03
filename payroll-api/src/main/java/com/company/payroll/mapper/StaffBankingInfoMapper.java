package com.company.payroll.mapper;

import java.util.List;

import com.company.payroll.model.StaffBankingInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffBankingInfoMapper {
    int deleteByPrimaryKey(Integer bId);
    int insert(StaffBankingInfo row);
    int insertSelective(StaffBankingInfo row);
    List<StaffBankingInfo> selectList();
    StaffBankingInfo selectByPrimaryKey(Integer bId);
    int updateByPrimaryKeySelective(StaffBankingInfo row);
    int updateByPrimaryKey(StaffBankingInfo row);
}