package com.company.payroll.service.impl

import com.company.payroll.model.StaffBankingInfo
import com.company.payroll.service.BankingInfoService
import com.github.pagehelper.PageInfo
import org.springframework.stereotype.Service

@Service
class BankingInfoServiceImpl: BankingInfoService {
  override fun findById(bid: Int): StaffBankingInfo? {
    TODO("Not yet implemented")
  }

  override fun list(page: Int, offset: Int): PageInfo<StaffBankingInfo> {
    TODO("Not yet implemented")
  }

  override fun update(bank: StaffBankingInfo): StaffBankingInfo {
    TODO("Not yet implemented")
  }
}