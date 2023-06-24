package com.company.payroll.mapper

import com.company.payroll.model.StaffDetails
import org.springframework.stereotype.Repository

@Repository
interface StaffDetailsMapper {
  fun deleteByPrimaryKey(staffId: Int): Int

  fun insert(row: StaffDetails): Int

  fun insertSelective(row: StaffDetails): Int

  fun selectByPrimaryKey(staffId: Int): StaffDetails?

  fun updateByPrimaryKeySelective(row: StaffDetails): Int

  fun updateByPrimaryKey(row: StaffDetails): Int
}