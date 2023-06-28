package com.company.payroll.service

import com.company.payroll.model.StaffSalary

import com.github.pagehelper.PageInfo

interface StaffSalaryService {
  /**
   *
   * @param sid
   * @return
   */
  fun findById(sid: Int): StaffSalary

  /**
   *
   * @return
   */
  fun list(page: Int, offset: Int): PageInfo<StaffSalary>

  /**
   *
   * @param staffSalary
   * @return
   */
  fun update(staffSalary: StaffSalary): StaffSalary
}