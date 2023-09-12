package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.AmsResignation;

@Repository
public interface AmsResignationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AmsResignation row);

    int insertSelective(AmsResignation row);

    AmsResignation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AmsResignation row);

    int updateByPrimaryKey(AmsResignation row);
}