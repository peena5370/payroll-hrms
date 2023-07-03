package com.company.payroll.mapper;

import com.company.payroll.model.StaffAttendance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StaffAttendanceMapper {
    int deleteByPrimaryKey(Integer atId);
    int insert(StaffAttendance row);
    int insertSelective(StaffAttendance row);
    StaffAttendance selectByPrimaryKey(Integer atId);
    List<StaffAttendance> selectByStaffIdAndDate(@Param("staffId") Integer staffId,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);
    int updateCheckTime(StaffAttendance row);
    int updateByPrimaryKeySelective(StaffAttendance row);
    int updateByPrimaryKey(StaffAttendance row);
}
