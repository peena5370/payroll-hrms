package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Loan;

@Repository
public interface LoanMapper {
    int deleteByPrimaryKey(Integer loanId);

    int insert(Loan row);

    int insertSelective(Loan row);
    
    List<Loan> selectList();
    
    List<Loan> selectListByEId(int eid);

    Loan selectByPrimaryKey(Integer loanId);

    int updateByPrimaryKeySelective(Loan row);

    int updateByPrimaryKey(Loan row);
}