package com.company.payroll.mapper

import com.company.payroll.model.StaffResignation
import org.springframework.stereotype.Repository

@Repository
interface StaffResignationMapper {
  fun deleteByPrimaryKey(resignId: Int): Int

  fun insert(row: StaffResignation): Int

  fun insertSelective(row: StaffResignation): Int

  fun selectByPrimaryKey(resignId: Int): StaffResignation

  fun selectList(): List<StaffResignation>

  fun updateByPrimaryKeySelective(row: StaffResignation): Int

  fun updateByPrimaryKey(row: StaffResignation): Int
}