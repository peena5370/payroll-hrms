package com.company.payroll.service

import com.company.payroll.model.StaffBankingInfo

import com.github.pagehelper.PageInfo

interface StaffBankingInfoService {
  /**
   *
   * @param bid
   * @return
   */
  fun findById(bid: Int): StaffBankingInfo

  /**
   * @param page
   * @param offset
   * @return
   */
  fun list(page: Int, offset: Int): PageInfo<StaffBankingInfo>

  /**
   *
   * @param staffBankingInfo
   * @return
   */
  fun update(staffBankingInfo: StaffBankingInfo): StaffBankingInfo
}