package com.company.payroll.service.impl

import com.company.payroll.mapper.StaffBankingInfoMapper
import com.company.payroll.mapper.StaffDetailsMapper
import com.company.payroll.mapper.StaffLeaveDetailsMapper
import com.company.payroll.mapper.StaffSalaryMapper
import com.company.payroll.model.StaffBankingInfo
import com.company.payroll.model.StaffDetails
import com.company.payroll.model.StaffLeaveDetails
import com.company.payroll.model.StaffSalary
import com.company.payroll.service.StaffDetailsService
import com.company.payroll.util.FileUtils
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.nio.file.Paths

@Service
class StaffDetailsServiceImpl(@Autowired private val staffDetailsMapper: StaffDetailsMapper,
                              @Autowired private val staffBankingInfoMapper: StaffBankingInfoMapper,
                              @Autowired private val staffSalaryMapper: StaffSalaryMapper,
                              @Autowired private val staffLeaveDetailsMapper: StaffLeaveDetailsMapper,
                              @Autowired private val fileUtils: FileUtils): StaffDetailsService {
  override fun countActiveStaff(deptNo: Int): Int {
    TODO("Not yet implemented")
  }

  override fun deleteStaffDetails(staffId: Int): Int {
    var row = 0

    val staffDetails: StaffDetails = staffDetailsMapper.selectByPrimaryKey(staffId)

    val status1: Boolean? = staffDetails.imgPath?.let { Paths.get(it) }?.let { fileUtils.delete(it) }
    val status2 = staffDetailsMapper.deleteByPrimaryKey(staffDetails.staffId)
    val status3 = staffBankingInfoMapper.deleteByPrimaryKey(staffDetails.bId)
    val status4 = staffSalaryMapper.deleteByPrimaryKey(staffDetails.sId)
    val status5 = staffLeaveDetailsMapper.deleteByPrimaryKey(staffDetails.ldId)

    if(status1==true && status2==1 && status3==1 && status4==1 && status5==1) {
      row = 1
    }

    return row
  }

  override fun findByStaffId(staffId: Int): StaffDetails {
    return staffDetailsMapper.selectByPrimaryKey(staffId)
  }

  override fun listStaffDetails(page: Int, offset: Int): PageInfo<StaffDetails> {
    PageHelper.startPage<StaffDetails>(page, offset)
    return PageInfo<StaffDetails>(staffDetailsMapper.selectList())
  }

  override fun addStaffDetails(staffDetails: StaffDetails): StaffDetails {
    staffDetails.staffBankingInfo?.let { staffBankingInfoMapper.insertSelective(it) }
    val bankingInfo: StaffBankingInfo? = staffDetails.staffBankingInfo?.bId?.let { staffBankingInfoMapper.selectByPrimaryKey(it) }

    staffDetails.staffSalary?.let { staffSalaryMapper.insertSelective(it) }
    val salaryInfo: StaffSalary? = staffDetails.staffSalary?.sId?.let { staffSalaryMapper.selectByPrimaryKey(it) }

    staffDetails.staffLeaveDetails?.let { staffLeaveDetailsMapper.insertSelective(it) }
    val leaveInfo: StaffLeaveDetails? = staffDetails.staffLeaveDetails?.ldId?.let { staffLeaveDetailsMapper.selectByPrimaryKey(it) }

    val manager: StaffDetails? = staffDetails.managerId?.let { staffDetailsMapper.selectByPrimaryKey(it) }

    staffDetails.managerId = manager?.staffId

    if (bankingInfo !=null && salaryInfo != null && leaveInfo != null) {
      staffDetails.ldId = leaveInfo.ldId
      staffDetails.sId = salaryInfo.sId
      staffDetails.bId = bankingInfo.bId
    }

    staffDetailsMapper.insertSelective(staffDetails)

    return staffDetails
  }

  override fun updateStaffDetails(staffDetails: StaffDetails): StaffDetails {
    staffDetailsMapper.updateByPrimaryKeySelective(staffDetails)
    return staffDetails
  }
}