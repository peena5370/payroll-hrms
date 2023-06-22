package com.company.payroll.service

import com.company.payroll.model.Promotion
import com.company.payroll.model.Resignation
import com.company.payroll.model.Training
import com.github.pagehelper.PageInfo
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

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
  fun findPromotionById(pId: Int): Promotion?

  /**
   *
   * @param resignId
   * @return
   */
  fun findResignationById(resignId: Int): Resignation?

  /**
   *
   * @param staffId
   * @return
   */
  fun findTrainingByStaffId(staffId: Int): List<Training>?

  /**
   *
   * @param tId
   * @return
   */
  fun findTrainingById(tId: Int): Training?

  /**
   *
   * @param promotion
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun insertPromotion(promotion: Promotion): Promotion

  /**
   *
   * @param resign
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun insertResignation(resign: Resignation): Resignation

  /**
   *
   * @param training
   * @return
   */
  fun insertTraining(training: Training): Training

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listPromotion(page: Int, offset: Int): PageInfo<Promotion>

  /**
   *
   * @param page
   * @param offset
   * @param staffId
   * @return
   */
  fun listPromotionByStaffId(page: Int, offset: Int, staffId: Int): PageInfo<Promotion>

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listResignation(page: Int, offset: Int): PageInfo<Resignation>

  /**
   *
   * @param page
   * @param offset
   * @return
   */
  fun listTraining(page: Int, offset: Int): PageInfo<Training>

  /**
   *
   * @param promotion
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun updatePromotion(promotion: Promotion): Promotion

  /**
   *
   * @param resign
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun updateResignation(resign: Resignation): Resignation

  /**
   *
   * @param training
   * @return
   */
  fun updateTraining(training: Training): Training
}