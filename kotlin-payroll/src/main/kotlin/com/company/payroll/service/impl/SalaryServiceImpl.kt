package com.company.payroll.service.impl

import com.company.payroll.model.StaffSalary
import com.company.payroll.service.SalaryService
import com.github.pagehelper.PageInfo
import org.springframework.stereotype.Service

@Service
class SalaryServiceImpl: SalaryService {
  override fun findById(sid: Int): StaffSalary? {
    TODO("Not yet implemented")
  }

  override fun list(page: Int, offset: Int): PageInfo<StaffSalary> {
    TODO("Not yet implemented")
  }

  override fun update(staffSalary: StaffSalary): StaffSalary {
    TODO("Not yet implemented")
  }
}