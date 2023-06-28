package com.company.payroll.service

import com.company.payroll.model.StaffPayroll
import com.github.pagehelper.PageInfo

interface StaffPayrollService {
  /**
   *
   * @param prId
   * @return
   */
  fun deleteStaffPayroll(prId: Int): Int

  /**
   *
   * @param prId
   * @return
   */
  fun findStaffPayrollById(prId: Int): StaffPayroll

  /**
   *
   * @param staffId
   * @return
   */
  fun findStaffPayrollByStaffId(staffId: Int): List<StaffPayroll>

  /**
   *
   * @param staffPayroll
   * @return
   */
  fun insertStaffPayroll(staffPayroll: StaffPayroll): StaffPayroll

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listStaffPayroll(page: Int, offset: Int): PageInfo<StaffPayroll>

  /**
   *
   * @param staffPayroll
   * @return
   */
  fun updateStaffPayroll(staffPayroll: StaffPayroll): StaffPayroll

}