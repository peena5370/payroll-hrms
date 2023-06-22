package com.company.payroll.service

import com.company.payroll.model.BankingInfo

import com.github.pagehelper.PageInfo

interface BankingInfoService {
  /**
   *
   * @param bid
   * @return
   */
  fun findById(bid: Int): BankingInfo?

  /**
   *
   * @return
   */
  fun list(page: Int, offset: Int): PageInfo<BankingInfo>

  /**
   *
   * @param bank
   * @return
   */
  fun update(bank: BankingInfo): BankingInfo
}