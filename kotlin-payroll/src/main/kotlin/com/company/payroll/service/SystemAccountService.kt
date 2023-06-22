package com.company.payroll.service

import com.company.payroll.model.Account
import com.github.pagehelper.PageInfo
import java.security.NoSuchAlgorithmException

interface SystemAccountService {
  /**
   *
   * @return
   */
  fun list(page: Int, offset: Int): PageInfo<Account>

  /**
   *
   * @param aId
   * @return
   */
  fun findById(aId: Int): Account?

  /**
   *
   * @param row
   * @return
   * @throws NoSuchAlgorithmException
   */
  @Throws(NoSuchAlgorithmException::class)
  fun insert(row: Account): Account

  /**
   *
   * @param account
   * @return
   * @throws NoSuchAlgorithmException
   */
  @Throws(NoSuchAlgorithmException::class)
  fun updateListPassword(account: Account): Account

  /**
   *
   * @param account
   * @return
   */
  fun update(account: Account): Account

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
  fun getByUsername(username: String): Account?

  /**
   *
   * @param account
   * @return
   */
  fun updateImagePath(account: Account): Account
}