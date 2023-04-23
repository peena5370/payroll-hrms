package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Manager;

//@Repository
public interface ManagerMapper {
    int deleteByPrimaryKey(Integer mId);

    int insert(Manager row);

    int insertSelective(Manager row);

    Manager selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(Manager row);

    int updateByPrimaryKey(Manager row);
}