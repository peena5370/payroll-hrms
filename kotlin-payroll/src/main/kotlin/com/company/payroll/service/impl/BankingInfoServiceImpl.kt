package com.company.payroll.service.impl

import com.company.payroll.model.BankingInfo
import com.company.payroll.service.BankingInfoService
import com.github.pagehelper.PageInfo
import org.springframework.stereotype.Service

@Service
class BankingInfoServiceImpl: BankingInfoService {
  override fun findById(bid: Int): BankingInfo? {
    TODO("Not yet implemented")
  }

  override fun list(page: Int, offset: Int): PageInfo<BankingInfo> {
    TODO("Not yet implemented")
  }

  override fun update(bank: BankingInfo): BankingInfo {
    TODO("Not yet implemented")
  }
}