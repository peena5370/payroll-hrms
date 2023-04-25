package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.PayrollEmployee;

@Repository
public interface PayrollEmployeeMapper {
    int deleteByPrimaryKey(Integer prId);

    int insert(PayrollEmployee row);

    int insertSelective(PayrollEmployee row);
    
    List<PayrollEmployee> selectList();
    
    List<PayrollEmployee> selectListByEId(int eid);

    PayrollEmployee selectByPrimaryKey(Integer prId);

    int updateByPrimaryKeySelective(PayrollEmployee row);

    int updateByPrimaryKey(PayrollEmployee row);
}