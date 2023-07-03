package com.company.payroll.mapper;

import java.util.List;

import com.company.payroll.model.StaffLoan;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffLoanMapper {
    int deleteByPrimaryKey(Integer loanId);
    int insert(StaffLoan row);
    int insertSelective(StaffLoan row);
    List<StaffLoan> selectList();
    List<StaffLoan> selectListByStaffId(Integer staffId);
    StaffLoan selectByPrimaryKey(Integer loanId);
    int updateByPrimaryKeySelective(StaffLoan row);
    int updateByPrimaryKey(StaffLoan row);
}