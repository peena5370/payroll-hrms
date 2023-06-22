package com.company.payroll.service.impl

import com.company.payroll.model.StaffDetails
import com.company.payroll.service.StaffDetailsService
import com.github.pagehelper.PageInfo
import org.springframework.stereotype.Service

@Service
class StaffDetailsServiceImpl: StaffDetailsService {
  override fun countActiveStaff(deptNo: Int): Int {
    TODO("Not yet implemented")
  }

  override fun deleteStaffDetails(staffId: Int): Int {
    TODO("Not yet implemented")
  }

  override fun findByStaffId(staffId: Int): StaffDetails? {
    TODO("Not yet implemented")
  }

  override fun listStaffDetails(page: Int, offset: Int): PageInfo<StaffDetails> {
    TODO("Not yet implemented")
  }

  override fun addStaffDetails(staffDetails: StaffDetails): StaffDetails {
    TODO("Not yet implemented")
  }

  override fun updateStaffDetails(staffDetails: StaffDetails): StaffDetails {
    TODO("Not yet implemented")
  }
}