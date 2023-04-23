package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Training;

//@Repository
public interface TrainingMapper {
    int deleteByPrimaryKey(Integer tId);

    int insert(Training row);

    int insertSelective(Training row);

    Training selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(Training row);

    int updateByPrimaryKeyWithBLOBs(Training row);

    int updateByPrimaryKey(Training row);
}