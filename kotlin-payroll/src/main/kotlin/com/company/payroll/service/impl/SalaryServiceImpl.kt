package com.company.payroll.service.impl

import com.company.payroll.model.Salary
import com.company.payroll.service.SalaryService
import com.github.pagehelper.PageInfo
import org.springframework.stereotype.Service

@Service
class SalaryServiceImpl: SalaryService {
  override fun findById(sid: Int): Salary? {
    TODO("Not yet implemented")
  }

  override fun list(page: Int, offset: Int): PageInfo<Salary> {
    TODO("Not yet implemented")
  }

  override fun update(salary: Salary): Salary {
    TODO("Not yet implemented")
  }
}