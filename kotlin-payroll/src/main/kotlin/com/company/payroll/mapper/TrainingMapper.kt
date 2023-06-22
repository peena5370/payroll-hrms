package com.company.payroll.mapper

import com.company.payroll.model.Training
import org.springframework.stereotype.Repository

@Repository
interface TrainingMapper {
  fun deleteByPrimaryKey(tId: Int): Int
  fun insert(row: Training): Int
  fun insertSelective(row: Training): Int
  fun selectList(): List<Training>?
  fun selectListByEId(staffId: Int): List<Training>?
  fun selectByPrimaryKey(tId: Int): Training?
  fun updateByPrimaryKeySelective(row: Training): Int
  fun updateByPrimaryKeyWithBLOBs(row: Training): Int
  fun updateByPrimaryKey(row: Training): Int
}