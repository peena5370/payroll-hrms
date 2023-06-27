package com.company.payroll.service

import com.company.payroll.model.SystemAccount
import com.github.pagehelper.PageInfo
import java.security.NoSuchAlgorithmException

interface SystemAccountService {
  /**
   *
   * @return
   */
  fun list(page: Int, offset: Int): PageInfo<SystemAccount>

  /**
   *
   * @param aId
   * @return
   */
  fun findById(aId: Int): SystemAccount

  /**
   *
   * @param row
   * @return
   * @throws NoSuchAlgorithmException
   */
  @Throws(NoSuchAlgorithmException::class)
  fun insert(row: SystemAccount): SystemAccount

  /**
   *
   * @param systemAccount
   * @return
   * @throws NoSuchAlgorithmException
   */
  @Throws(NoSuchAlgorithmException::class)
  fun updateListPassword(systemAccount: SystemAccount): SystemAccount

  /**
   *
   * @param systemAccount
   * @return
   */
  fun update(systemAccount: SystemAccount): SystemAccount

  /**
   *
   * @param aId
   * @return
   */
  fun delete(aId: Int): Int

  /**
   *
   * @param username
   * @return
   */
  fun getByUsername(username: String): SystemAccount?

  /**
   *
   * @param systemAccount
   * @return
   */
  fun updateImagePath(systemAccount: SystemAccount): SystemAccount
}