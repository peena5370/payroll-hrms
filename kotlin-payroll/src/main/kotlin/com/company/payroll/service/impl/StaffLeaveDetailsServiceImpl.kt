package com.company.payroll.service.impl

import com.company.payroll.mapper.StaffLeaveDetailsMapper
import com.company.payroll.model.StaffLeaveDetails
import com.company.payroll.service.StaffLeaveDetailsService
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired

class StaffLeaveDetailsServiceImpl(@Autowired private val staffLeaveDetailsMapper: StaffLeaveDetailsMapper): StaffLeaveDetailsService {
  override fun list(page: Int, offset: Int): PageInfo<StaffLeaveDetails> {
    PageHelper.startPage<StaffLeaveDetails>(page, offset)
    return PageInfo<StaffLeaveDetails>(staffLeaveDetailsMapper.selectList())
  }

  override fun findById(ldId: Int): StaffLeaveDetails {
    return staffLeaveDetailsMapper.selectByPrimaryKey(ldId)
  }

  override fun update(staffLeaveDetails: StaffLeaveDetails): StaffLeaveDetails {
    staffLeaveDetailsMapper.updateByPrimaryKeySelective(staffLeaveDetails)
    return staffLeaveDetails
  }
}