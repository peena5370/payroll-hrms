package com.company.payroll.service.impl

import com.company.payroll.mapper.StaffSalaryMapper
import com.company.payroll.model.StaffSalary
import com.company.payroll.service.StaffSalaryService
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StaffSalaryServiceImpl(@Autowired private val staffSalaryMapper: StaffSalaryMapper): StaffSalaryService {
  override fun findById(sid: Int): StaffSalary {
    return staffSalaryMapper.selectByPrimaryKey(sid)
  }

  override fun list(page: Int, offset: Int): PageInfo<StaffSalary> {
    PageHelper.startPage<StaffSalary>(page, offset)
    return PageInfo<StaffSalary>(staffSalaryMapper.selectList())
  }

  override fun update(staffSalary: StaffSalary): StaffSalary {
    staffSalaryMapper.updateByPrimaryKeySelective(staffSalary)
    return staffSalary
  }
}