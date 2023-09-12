package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.HmsStaffBanking;

@Repository
public interface HmsStaffBankingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HmsStaffBanking row);

    int insertSelective(HmsStaffBanking row);

    HmsStaffBanking selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HmsStaffBanking row);

    int updateByPrimaryKey(HmsStaffBanking row);
}