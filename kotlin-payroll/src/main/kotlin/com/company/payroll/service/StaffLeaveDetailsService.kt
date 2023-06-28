package com.company.payroll.service

import com.company.payroll.model.StaffLeaveDetails
import com.github.pagehelper.PageInfo

interface StaffLeaveDetailsService {
  /**
   * @param page
   * @param offset
   * @return
   */
  fun list(page: Int, offset: Int): PageInfo<StaffLeaveDetails>

  /**
   * @param ldId
   * @return
   */
  fun findById(ldId: Int): StaffLeaveDetails

  /**
   * @param staffLeaveDetails
   * @return
   */
  fun update(staffLeaveDetails: StaffLeaveDetails): StaffLeaveDetails
}