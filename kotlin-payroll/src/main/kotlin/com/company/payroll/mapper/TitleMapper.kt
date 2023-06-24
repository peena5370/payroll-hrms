package com.company.payroll.mapper

import com.company.payroll.model.Title
import org.springframework.stereotype.Repository

@Repository
interface TitleMapper {
  fun deleteByPrimaryKey(titleNo: Int): Int

  fun insert(row: Title): Int

  fun insertSelective(row: Title): Int

  fun selectByPrimaryKey(titleNo: Int): Title?

  fun updateByPrimaryKeySelective(row: Title): Int

  fun updateByPrimaryKey(row: Title): Int
}