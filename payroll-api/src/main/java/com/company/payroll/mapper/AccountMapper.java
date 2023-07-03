package com.company.payroll.mapper;

import java.util.List;

import com.company.payroll.model.SystemAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    int deleteByPrimaryKey(Integer aId);
    int insert(SystemAccount row);
    int insertSelective(SystemAccount row);
    List<SystemAccount> selectList();
    SystemAccount selectByPrimaryKey(Integer aId);
    SystemAccount selectByUsername(String username);
    int updateByPrimaryKeySelective(SystemAccount row);
    int updateImagePathByUsername(SystemAccount row);
    int updatePasswordByUsername(SystemAccount row);
    int updateByPrimaryKey(SystemAccount row);
}