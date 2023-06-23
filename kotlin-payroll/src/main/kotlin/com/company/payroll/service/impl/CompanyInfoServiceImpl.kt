package com.company.payroll.service.impl

import com.company.payroll.model.Department
import com.company.payroll.model.Title
import com.company.payroll.service.CompanyInfoService
import com.github.pagehelper.PageInfo
import org.springframework.stereotype.Service

@Service
class CompanyInfoServiceImpl: CompanyInfoService {
  override fun deleteDepartment(deptNo: Int): Int {
    TODO("Not yet implemented")
  }

  override fun deleteTitle(titleNo: Int): Int {
    TODO("Not yet implemented")
  }

  override fun findDepartmentById(deptNo: Int): Department? {
    TODO("Not yet implemented")
  }

  override fun findTitleById(titleNo: Int): Title? {
    TODO("Not yet implemented")
  }

  override fun insertDepartment(department: Department): Department {
    TODO("Not yet implemented")
  }

  override fun insertTitle(title: Title): Title {
    TODO("Not yet implemented")
  }

  override fun listDepartment(page: Int, offset: Int): PageInfo<Department> {
    TODO("Not yet implemented")
  }

  override fun listTitle(page: Int, offset: Int): PageInfo<Title> {
    TODO("Not yet implemented")
  }

  override fun updateDepartment(department: Department): Department {
    TODO("Not yet implemented")
  }

  override fun updateTitle(title: Title): Title {
    TODO("Not yet implemented")
  }
}