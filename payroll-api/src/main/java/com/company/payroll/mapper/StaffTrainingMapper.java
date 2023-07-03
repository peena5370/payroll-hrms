package com.company.payroll.mapper;

import java.util.List;

import com.company.payroll.model.StaffTraining;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffTrainingMapper {
    int deleteByPrimaryKey(Integer tId);
    int insert(StaffTraining row);
    int insertSelective(StaffTraining row);
    List<StaffTraining> selectList();
    List<StaffTraining> selectListByStaffId(Integer staffId);
    StaffTraining selectByPrimaryKey(Integer tId);
    int updateByPrimaryKeySelective(StaffTraining row);
    int updateByPrimaryKeyWithBLOBs(StaffTraining row);
    int updateByPrimaryKey(StaffTraining row);

    /**
     * @deprecated will be removed after StaffTrainingMapper interface success implemented.
     * <p>Please use {@link #selectListByStaffId(Integer)} instead.</p>
     * @param mid
     * @return
     */
    @Deprecated
    List<StaffTraining> selectListByMId(int mid);
}