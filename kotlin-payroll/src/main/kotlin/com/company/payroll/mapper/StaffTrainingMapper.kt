package com.company.payroll.mapper

import com.company.payroll.model.StaffTraining
import org.springframework.stereotype.Repository

@Repository
interface StaffTrainingMapper {
  fun deleteByPrimaryKey(tId: Int): Int

  fun insert(row: StaffTraining): Int

  fun insertSelective(row: StaffTraining): Int

  fun selectByPrimaryKey(tId: Int): StaffTraining

  fun selectList(): List<StaffTraining>

  fun selectListByStaffId(staffId: Int): List<StaffTraining>

  fun updateByPrimaryKeySelective(row: StaffTraining): Int

  fun updateByPrimaryKeyWithBLOBs(row: StaffTraining): Int

  fun updateByPrimaryKey(row: StaffTraining): Int
}