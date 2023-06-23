package com.company.payroll.service

import com.company.payroll.model.StaffLeave
import com.company.payroll.model.StaffLoan
import com.github.pagehelper.PageInfo

interface StaffApplicationService {
  /**
   *
   * @param lid
   * @return
   */
  fun deleteLeave(lid: Int): Int

  /**
   *
   * @param lid
   * @return
   */
  fun deleteLoan(lid: Int): Int

  /**
   *
   * @param eid
   * @return
   */
  fun findLeaveByEId(eid: Int): List<StaffLeave>?

  /**
   *
   * @param lid
   * @return
   */
  fun findLeaveById(lid: Int): StaffLeave?

  /**
   *
   * @param eid
   * @return
   */
  fun findLoanByEId(eid: Int): List<StaffLoan>?

  /**
   *
   * @param lid
   * @return
   */
  fun findLoanById(lid: Int): StaffLoan?

  /**
   *
   * @param staffLeave
   * @return
   */
  fun insertLeave(staffLeave: StaffLeave): StaffLeave

  /**
   *
   * @param staffLoan
   * @return
   */
  fun insertLoan(staffLoan: StaffLoan): StaffLoan

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listLeave(page: Int, offset: Int): PageInfo<StaffLeave>

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listLoan(page: Int, offset: Int): PageInfo<StaffLoan>

  /**
   *
   * @param staffLeave
   * @return
   */
  fun updateLeave(staffLeave: StaffLeave): StaffLeave

  /**
   *
   * @param staffLoan
   * @return
   */
  fun updateLoan(staffLoan: StaffLoan): StaffLoan
}