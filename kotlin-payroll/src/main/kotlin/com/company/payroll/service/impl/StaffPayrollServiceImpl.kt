package com.company.payroll.service.impl

import com.company.payroll.mapper.StaffPayrollMapper
import com.company.payroll.model.StaffPayroll
import com.company.payroll.service.StaffPayrollService
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StaffPayrollServiceImpl(@Autowired private val staffPayrollMapper: StaffPayrollMapper): StaffPayrollService {
  override fun deleteStaffPayroll(prId: Int): Int {
    return staffPayrollMapper.deleteByPrimaryKey(prId)
  }

  override fun findStaffPayrollById(prId: Int): StaffPayroll {
    return staffPayrollMapper.selectByPrimaryKey(prId)
  }

  override fun findStaffPayrollByStaffId(staffId: Int): List<StaffPayroll> {
    return staffPayrollMapper.selectListByStaffId(staffId)
  }

  override fun insertStaffPayroll(staffPayroll: StaffPayroll): StaffPayroll {
    staffPayrollMapper.insertSelective(staffPayroll)
    return staffPayroll
  }

  override fun listStaffPayroll(page: Int, offset: Int): PageInfo<StaffPayroll> {
    PageHelper.startPage<StaffPayroll>(page, offset)
    return PageInfo<StaffPayroll>(staffPayrollMapper.selectList())
  }

  override fun updateStaffPayroll(staffPayroll: StaffPayroll): StaffPayroll {
    staffPayrollMapper.updateByPrimaryKeySelective(staffPayroll)
    return staffPayroll
  }
}