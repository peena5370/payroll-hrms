package com.company.payroll.service

import com.company.payroll.model.Department
import com.company.payroll.model.PageWrapper
import com.company.payroll.model.Title
import com.github.pagehelper.PageInfo

interface CompanyInfoService {
  /**
   *
   * @param deptNo
   * @return
   */
  fun deleteDepartment(deptNo: Int): Int

  /**
   *
   * @param titleNo
   * @return
   */
  fun deleteTitle(titleNo: Int): Int

  /**
   *
   * @param deptNo
   * @return
   */
  fun findDepartmentById(deptNo: Int): Department

  /**
   *
   * @param titleNo
   * @return
   */
  fun findTitleById(titleNo: Int): Title

  /**
   *
   * @param department
   * @return
   */
  fun insertDepartment(department: Department): Department

  /**
   *
   * @param title
   * @return
   */
  fun insertTitle(title: Title): Title

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listDepartment(page: Int, offset: Int): PageInfo<Department>

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listTitle(page: Int, offset: Int): PageInfo<Title>

  /**
   *
   * @param department
   * @return
   */
  fun updateDepartment(department: Department): Department

  /**
   *
   * @param title
   * @return
   */
  fun updateTitle(title: Title): Title
}