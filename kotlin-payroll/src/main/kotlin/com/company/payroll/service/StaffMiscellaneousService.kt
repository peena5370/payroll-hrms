package com.company.payroll.service

import com.company.payroll.model.StaffPromotion
import com.company.payroll.model.StaffResignation
import com.company.payroll.model.StaffTraining
import com.github.pagehelper.PageInfo
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

interface StaffMiscellaneousService {
  /**
   *
   * @param pId
   * @return
   */
  fun deletePromotion(pId: Int): Int

  /**
   *
   * @param resignId
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun deleteResignation(resignId: Int): Int

  /**
   *
   * @param tId
   * @return
   */
  fun deleteTraining(tId: Int): Int

  /**
   *
   * @param pId
   * @return
   */
  fun findPromotionById(pId: Int): StaffPromotion

  /**
   *
   * @param resignId
   * @return
   */
  fun findResignationById(resignId: Int): StaffResignation

  /**
   *
   * @param tId
   * @return
   */
  fun findTrainingById(tId: Int): StaffTraining

  /**
   *
   * @param staffPromotion
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun insertPromotion(staffPromotion: StaffPromotion): StaffPromotion

  /**
   *
   * @param resign
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun insertResignation(attachment: MultipartFile, staffResignation: StaffResignation): StaffResignation

  /**
   *
   * @param staffTraining
   * @return
   */
  fun insertTraining(staffTraining: StaffTraining): StaffTraining

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listPromotion(page: Int, offset: Int): PageInfo<StaffPromotion>

  /**
   *
   * @param page
   * @param offset
   * @param staffId
   * @return
   */
  fun listPromotionByStaffId(page: Int, offset: Int, staffId: Int): PageInfo<StaffPromotion>

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listResignation(page: Int, offset: Int): PageInfo<StaffResignation>

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listTraining(page: Int, offset: Int): PageInfo<StaffTraining>

  /**
   *
   * @param staffId
   * @return
   */
  fun listTrainingByStaffId(staffId: Int): List<StaffTraining>

  /**
   *
   * @param staffPromotion
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun updatePromotion(staffPromotion: StaffPromotion): StaffPromotion

  /**
   *
   * @param resign
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun updateResignation(staffResignation: StaffResignation): StaffResignation

  /**
   *
   * @param staffTraining
   * @return
   */
  fun updateTraining(staffTraining: StaffTraining): StaffTraining
}