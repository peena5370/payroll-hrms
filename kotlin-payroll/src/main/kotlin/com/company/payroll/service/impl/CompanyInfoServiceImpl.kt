package com.company.payroll.service.impl

import com.company.payroll.mapper.DepartmentMapper
import com.company.payroll.mapper.TitleMapper
import com.company.payroll.model.Department
import com.company.payroll.model.Title
import com.company.payroll.service.CompanyInfoService
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompanyInfoServiceImpl(@Autowired private val departmentMapper: DepartmentMapper,
                             @Autowired private val titleMapper: TitleMapper): CompanyInfoService {
  override fun deleteDepartment(deptNo: Int): Int {
    return departmentMapper.deleteByPrimaryKey(deptNo)
  }

  override fun deleteTitle(titleNo: Int): Int {
    return titleMapper.deleteByPrimaryKey(titleNo)
  }

  override fun findDepartmentById(deptNo: Int): Department {
    return departmentMapper.selectByPrimaryKey(deptNo)
  }

  override fun findTitleById(titleNo: Int): Title {
    return titleMapper.selectByPrimaryKey(titleNo)
  }

  override fun insertDepartment(department: Department): Department {
    departmentMapper.insertSelective(department)
    return department
  }

  override fun insertTitle(title: Title): Title {
    titleMapper.insertSelective(title)
    return title
  }

  override fun listDepartment(page: Int, offset: Int): PageInfo<Department> {
    PageHelper.startPage<Department>(page, offset)
    return PageInfo<Department>(departmentMapper.selectList())
  }

  override fun listTitle(page: Int, offset: Int): PageInfo<Title> {
    PageHelper.startPage<Title>(page, offset)
    return PageInfo<Title>(titleMapper.selectList())
  }

  override fun updateDepartment(department: Department): Department {
    departmentMapper.updateByPrimaryKey(department)
    return department
  }

  override fun updateTitle(title: Title): Title {
    titleMapper.updateByPrimaryKey(title)
    return title
  }
}