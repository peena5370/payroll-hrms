package com.company.payroll.service.impl

import com.company.payroll.model.Promotion
import com.company.payroll.model.Resignation
import com.company.payroll.model.Training
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

  override fun findPromotionById(pId: Int): Promotion? {
    TODO("Not yet implemented")
  }

  override fun findResignationById(resignId: Int): Resignation? {
    TODO("Not yet implemented")
  }

  override fun findTrainingByStaffId(staffId: Int): List<Training>? {
    TODO("Not yet implemented")
  }

  override fun findTrainingById(tId: Int): Training? {
    TODO("Not yet implemented")
  }

  override fun insertPromotion(promotion: Promotion): Promotion {
    TODO("Not yet implemented")
  }

  override fun insertResignation(resign: Resignation): Resignation {
    TODO("Not yet implemented")
  }

  override fun insertTraining(training: Training): Training {
    TODO("Not yet implemented")
  }

  override fun listPromotion(page: Int, offset: Int): PageInfo<Promotion> {
    TODO("Not yet implemented")
  }

  override fun listPromotionByStaffId(page: Int, offset: Int, staffId: Int): PageInfo<Promotion> {
    TODO("Not yet implemented")
  }

  override fun listResignation(page: Int, offset: Int): PageInfo<Resignation> {
    TODO("Not yet implemented")
  }

  override fun listTraining(page: Int, offset: Int): PageInfo<Training> {
    TODO("Not yet implemented")
  }

  override fun updatePromotion(promotion: Promotion): Promotion {
    TODO("Not yet implemented")
  }

  override fun updateResignation(resign: Resignation): Resignation {
    TODO("Not yet implemented")
  }

  override fun updateTraining(training: Training): Training {
    TODO("Not yet implemented")
  }
}