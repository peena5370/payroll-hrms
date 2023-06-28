package com.company.payroll.service.impl

import com.company.payroll.mapper.StaffBankingInfoMapper
import com.company.payroll.model.StaffBankingInfo
import com.company.payroll.service.StaffBankingInfoService
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StaffBankingInfoServiceImpl(@Autowired private val staffBankingInfoMapper: StaffBankingInfoMapper): StaffBankingInfoService {
  override fun findById(bid: Int): StaffBankingInfo {
    return staffBankingInfoMapper.selectByPrimaryKey(bid)
  }

  override fun list(page: Int, offset: Int): PageInfo<StaffBankingInfo> {
    PageHelper.startPage<StaffBankingInfo>(page, offset)
    return PageInfo<StaffBankingInfo>(staffBankingInfoMapper.selectList())
  }

  override fun update(staffBankingInfo: StaffBankingInfo): StaffBankingInfo {
    staffBankingInfoMapper.updateByPrimaryKeySelective(staffBankingInfo)
    return staffBankingInfo
  }
}