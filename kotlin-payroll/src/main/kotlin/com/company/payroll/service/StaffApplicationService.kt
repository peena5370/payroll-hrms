package com.company.payroll.service

import com.company.payroll.model.Leave
import com.company.payroll.model.Loan
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
  fun findLeaveByEId(eid: Int): List<Leave>?

  /**
   *
   * @param lid
   * @return
   */
  fun findLeaveById(lid: Int): Leave?

  /**
   *
   * @param eid
   * @return
   */
  fun findLoanByEId(eid: Int): List<Loan>?

  /**
   *
   * @param lid
   * @return
   */
  fun findLoanById(lid: Int): Loan?

  /**
   *
   * @param leave
   * @return
   */
  fun insertLeave(leave: Leave): Leave

  /**
   *
   * @param loan
   * @return
   */
  fun insertLoan(loan: Loan): Loan

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listLeave(page: Int, offset: Int): PageInfo<Leave>

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listLoan(page: Int, offset: Int): PageInfo<Loan>

  /**
   *
   * @param leave
   * @return
   */
  fun updateLeave(leave: Leave): Leave

  /**
   *
   * @param loan
   * @return
   */
  fun updateLoan(loan: Loan): Loan
}