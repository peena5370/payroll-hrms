package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Account;

@Repository
public interface AccountMapper {
    int deleteByPrimaryKey(Integer aId);

    int insert(Account row);

    int insertSelective(Account row);
    
    List<Account> selectList();

    Account selectByPrimaryKey(Integer aId);
    
    Account selectByUsername(String username);

    int updateByPrimaryKeySelective(Account row);

    int updateByPrimaryKey(Account row);
    
    int updateImagePathByUsername(Account row);
}