package com.company.payroll.service

import com.company.payroll.model.StaffBankingInfo

import com.github.pagehelper.PageInfo

interface BankingInfoService {
  /**
   *
   * @param bid
   * @return
   */
  fun findById(bid: Int): StaffBankingInfo?

  /**
   *
   * @return
   */
  fun list(page: Int, offset: Int): PageInfo<StaffBankingInfo>

  /**
   *
   * @param bank
   * @return
   */
  fun update(bank: StaffBankingInfo): StaffBankingInfo
}