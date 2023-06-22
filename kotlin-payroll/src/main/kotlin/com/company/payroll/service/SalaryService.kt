package com.company.payroll.service

import com.company.payroll.model.Salary

import com.github.pagehelper.PageInfo

interface SalaryService {
  /**
   *
   * @param sid
   * @return
   */
  fun findById(sid: Int): Salary?

  /**
   *
   * @return
   */
  fun list(page: Int, offset: Int): PageInfo<Salary>

  /**
   *
   * @param salary
   * @return
   */
  fun update(salary: Salary): Salary
}