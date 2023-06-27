package com.company.payroll.service.impl

import com.company.payroll.model.StaffPromotion
import com.company.payroll.model.StaffResignation
import com.company.payroll.model.StaffTraining
import com.company.payroll.service.StaffMiscellaneousService
import com.github.pagehelper.PageInfo
import org.springframework.stereotype.Service

@Service
class StaffMiscellaneousServiceImpl: StaffMiscellaneousService {
  override fun deletePromotion(pId: Int): Int {
    TODO("Not yet implemented")
  }

  override fun deleteResignation(resignId: Int): Int {
    TODO("Not yet implemented")
  }

  override fun deleteTraining(tId: Int): Int {
    TODO("Not yet implemented")
  }

  override fun findPromotionById(pId: Int): StaffPromotion? {
    TODO("Not yet implemented")
  }

  override fun findResignationById(resignId: Int): StaffResignation? {
    TODO("Not yet implemented")
  }

  override fun findTrainingByStaffId(staffId: Int): List<StaffTraining>? {
    TODO("Not yet implemented")
  }

  override fun findTrainingById(tId: Int): StaffTraining? {
    TODO("Not yet implemented")
  }

  override fun insertPromotion(staffPromotion: StaffPromotion): StaffPromotion {
    TODO("Not yet implemented")
  }

  override fun insertResignation(resign: StaffResignation): StaffResignation {
    TODO("Not yet implemented")
  }

  override fun insertTraining(staffTraining: StaffTraining): StaffTraining {
    TODO("Not yet implemented")
  }

  override fun listPromotion(page: Int, offset: Int): PageInfo<StaffPromotion> {
    TODO("Not yet implemented")
  }

  override fun listPromotionByStaffId(page: Int, offset: Int, staffId: Int): PageInfo<StaffPromotion> {
    TODO("Not yet implemented")
  }

  override fun listResignation(page: Int, offset: Int): PageInfo<StaffResignation> {
    TODO("Not yet implemented")
  }

  override fun listTraining(page: Int, offset: Int): PageInfo<StaffTraining> {
    TODO("Not yet implemented")
  }

  override fun updatePromotion(staffPromotion: StaffPromotion): StaffPromotion {
    TODO("Not yet implemented")
  }

  override fun updateResignation(resign: StaffResignation): StaffResignation {
    TODO("Not yet implemented")
  }

  override fun updateTraining(staffTraining: StaffTraining): StaffTraining {
    TODO("Not yet implemented")
  }
}