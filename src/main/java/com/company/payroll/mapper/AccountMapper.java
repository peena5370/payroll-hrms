package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Account;

@Repository
public interface AccountMapper {
    int deleteByPrimaryKey(Integer aId);

    int insert(Account row);

    int insertSelective(Account row);

    Account selectByPrimaryKey(Integer aId);

    int updateByPrimaryKeySelective(Account row);

    int updateByPrimaryKey(Account row);
}