package com.company.payroll.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import com.company.payroll.model.Account;
import com.company.payroll.model.SystemAccount;
import com.github.pagehelper.PageInfo;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface SystemAccountService {
	
	/**
	 * Method of listing system account
	 * @param page page number
	 * @param offset page data offset
	 * @return List of system account
	 */
	PageInfo<SystemAccount> list(int page, int offset);
	
	/**
	 * Method of retrieve system account object based on account id
	 * @param aId account id
	 * @return {@link SystemAccount} object
	 */
	Optional<SystemAccount> findById(Integer aId);

	/**
	 * Method of retrieve system account object based on username
	 * @param username String of username
	 * @return {@link SystemAccount} object
	 */
	Optional<SystemAccount> findByUsername(String username);
	
	/**
	 * Method of register account into back end server
	 * @param systemAccount {@link SystemAccount} object
	 * @return {@link SystemAccount} object after inserted
	 */
	SystemAccount insert(SystemAccount systemAccount) throws NoSuchAlgorithmException;
	
	/**
	 * Method for administrator to modify user account password
	 * @param systemAccount {@link SystemAccount} object
	 * @return {@link SystemAccount} object
	 * @throws NoSuchAlgorithmException 
	 */
	SystemAccount updateListPassword(SystemAccount systemAccount) throws NoSuchAlgorithmException;
	
	/**
	 * @deprecated Please use {@link #modifyStatusRoles(SystemAccount)} instead.
	 * @param account
	 * @return
	 */
	Account update(Account account);

	/**
	 * Method for administrator to modify account information besides password.
	 * @param systemAccount {@link SystemAccount} object
	 * @return {@link SystemAccount} object after updated.
	 */
	SystemAccount modifyStatusRoles(SystemAccount systemAccount);

	/**
	 * Method of deleting system account information from back end server.
	 * @param aId account id
	 * @return Deleted row
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	Integer delete(Integer aId);
	
	/**
	 * @deprecated Please use {@link #findByUsername(String)} instead.
	 * @param username
	 * @return
	 */
	Account getByUsername(String username);
	
	/**
	 * @deprecated Please use {@link #updateImagePath(MultipartFile, SystemAccount)} instead.
	 * @param account
	 * @return
	 */
	Account updateImagePath(Account account);

	/**
	 * Method for user to update account image
	 * @param image image file
	 * @param systemAccount {@link SystemAccount} object
	 * @return {@link SystemAccount} object after updated account image
	 */
	SystemAccount updateImagePath(MultipartFile image, SystemAccount systemAccount);
	
	/** 
	 * 
	 * @return
	 * */
//	Integer countAccount();
	
	/** 
	 * 
	 * @return
	 * */
//	Integer countAccountByStatus();

	/**
	 * Method for user login setting last attempt when wrong password.
	 * @param systemAccount {@link SystemAccount} object
	 * @return {@link SystemAccount} object after updated
	 */
	SystemAccount setLastAttempt(SystemAccount systemAccount);

	/**
	 * Method for user to modify password
	 * <p>Parameter needed: <b>username and password</b></p>
	 * @param systemAccount {@link SystemAccount} object
	 * @return {@link SystemAccount} object after updated
	 */
	SystemAccount modifyPassword(SystemAccount systemAccount) throws NoSuchAlgorithmException;

	/**
	 * Method for user to download profile image
	 * @param username String of username
	 * @return Image resource
	 */
	Resource downloadAccountImage(String username);
}
