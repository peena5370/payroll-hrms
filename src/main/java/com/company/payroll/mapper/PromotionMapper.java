package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Promotion;

@Repository
public interface PromotionMapper {
    int deleteByPrimaryKey(Integer pId);

    int insert(Promotion row);

    int insertSelective(Promotion row);
    
    List<Promotion> selectList();

    Promotion selectByPrimaryKey(Integer pId);

    int updateByPrimaryKeySelective(Promotion row);

    int updateByPrimaryKey(Promotion row);
}