package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Resignation;

@Repository
public interface ResignationMapper {
    int deleteByPrimaryKey(Integer resignId);

    int insert(Resignation row);

    int insertSelective(Resignation row);

    Resignation selectByPrimaryKey(Integer resignId);

    int updateByPrimaryKeySelective(Resignation row);

    int updateByPrimaryKey(Resignation row);
}