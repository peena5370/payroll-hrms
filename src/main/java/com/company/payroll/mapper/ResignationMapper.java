package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Resignation;

@Repository
public interface ResignationMapper {
    int deleteByPrimaryKey(Integer resignId);

    int insert(Resignation row);

    int insertSelective(Resignation row);
    
    List<Resignation> selectList();

    Resignation selectByPrimaryKey(Integer resignId);

    int updateByPrimaryKeySelective(Resignation row);

    int updateByPrimaryKey(Resignation row);
}