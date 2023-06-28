package com.company.payroll.service.impl

import com.company.payroll.mapper.StaffLeaveMapper
import com.company.payroll.mapper.StaffLoanMapper
import com.company.payroll.model.StaffLeave
import com.company.payroll.model.StaffLoan
import com.company.payroll.service.StaffApplicationService
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StaffApplicationServiceImpl(@Autowired private val staffLeaveMapper: StaffLeaveMapper,
                                  @Autowired private val staffLoanMapper: StaffLoanMapper): StaffApplicationService {
  override fun deleteLeave(lid: Int): Int {
    return staffLeaveMapper.deleteByPrimaryKey(lid)
  }

  override fun deleteLoan(loanId: Int): Int {
    return staffLoanMapper.deleteByPrimaryKey(loanId)
  }

  override fun findLeaveByStaffId(staffId: Int): List<StaffLeave> {
    return staffLeaveMapper.selectListByStaffId(staffId)
  }

  override fun findLeaveById(lid: Int): StaffLeave {
    return staffLeaveMapper.selectByPrimaryKey(lid)
  }

  override fun findLoanByStaffId(staffId: Int): List<StaffLoan> {
    return staffLoanMapper.selectListByStaffId(staffId)
  }

  override fun findLoanById(loanId: Int): StaffLoan {
    return staffLoanMapper.selectByPrimaryKey(loanId)
  }

  override fun insertLeave(staffLeave: StaffLeave): StaffLeave {
    staffLeaveMapper.insertSelective(staffLeave)
    return staffLeave
  }

  override fun insertLoan(staffLoan: StaffLoan): StaffLoan {
    staffLoanMapper.insertSelective(staffLoan)
    return staffLoan
  }

  override fun listLeave(page: Int, offset: Int): PageInfo<StaffLeave> {
    PageHelper.startPage<StaffLeave>(page, offset)
    return PageInfo<StaffLeave>(staffLeaveMapper.selectList())
  }

  override fun listLoan(page: Int, offset: Int): PageInfo<StaffLoan> {
    PageHelper.startPage<StaffLoan>(page, offset)
    return PageInfo<StaffLoan>(staffLoanMapper.selectList())
  }

  override fun updateLeave(staffLeave: StaffLeave): StaffLeave {
    staffLeaveMapper.updateByPrimaryKeySelective(staffLeave)
    return staffLeave
  }

  override fun updateLoan(staffLoan: StaffLoan): StaffLoan {
    staffLoanMapper.updateByPrimaryKeySelective(staffLoan)
    return staffLoan
  }
}