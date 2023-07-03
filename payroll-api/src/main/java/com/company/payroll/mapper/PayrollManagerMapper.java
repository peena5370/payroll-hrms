package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.PayrollManager;

/**
 * @deprecated will de deleted after StaffPayrollMapper success implement.
 * <p> Please use {@link StaffPayrollMapper} instead of this interface</p>
 */
@Deprecated
@Repository
public interface PayrollManagerMapper {
    int deleteByPrimaryKey(Integer prMgrId);

    int insert(PayrollManager row);

    int insertSelective(PayrollManager row);
    
    List<PayrollManager> selectList();
    
    List<PayrollManager> selectListByMId(int mId);

    PayrollManager selectByPrimaryKey(Integer prMgrId);

    int updateByPrimaryKeySelective(PayrollManager row);

    int updateByPrimaryKey(PayrollManager row);
}