package com.company.payroll.service

import com.company.payroll.model.SystemAccount
import com.github.pagehelper.PageInfo
import org.springframework.core.io.Resource
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
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
   * @param username
   * @return
   */
  fun findByUsername(username: String): SystemAccount

  /**
   *
   * @param systemAccount
   * @return
   * @throws NoSuchAlgorithmException
   */
  @Throws(NoSuchAlgorithmException::class)
  fun insert(systemAccount: SystemAccount): SystemAccount

  /**
   * <p> Method for administrator to modify user account password
   * @param systemAccount
   * @return
   * @throws NoSuchAlgorithmException
   */
  @Throws(NoSuchAlgorithmException::class)
  fun updateListPassword(systemAccount: SystemAccount): SystemAccount

  /**
   * <p> Method for administrator to modify account information besides password
   * @param systemAccount
   * @return
   */
  fun modifyStatusRoles(systemAccount: SystemAccount): SystemAccount

  /**
   *
   * @param aId
   * @return
   */
  @Transactional(rollbackFor = [Exception::class], propagation = Propagation.REQUIRES_NEW)
  fun delete(aId: Int): Int

  /**
   * <p> Created 28 June 2023
   * <p> Method for user to update account image
   * @param systemAccount
   * @return
   */
  fun updateImagePath(image: MultipartFile, systemAccount: SystemAccount): SystemAccount

  /**
   * <p> Created 28 June 2023
   * <p> Method for user to modify password.
   * <p> Parameter needed: username and password
   * @param systemAccount
   * @return
   */
  fun modifyPassword(systemAccount: SystemAccount): SystemAccount

  /**
   * <p> Created 29 June 2023
   * <p> Method for user to download profile image
   * @param username
   * @return
   */
  fun downloadAccountImage(username: String): Resource?
}