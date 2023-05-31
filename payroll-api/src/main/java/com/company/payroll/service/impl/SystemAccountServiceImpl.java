package com.company.payroll.service.impl;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.AccountMapper;
import com.company.payroll.model.Account;
import com.company.payroll.service.SystemAccountService;
import com.company.payroll.util.PasswordEncryption;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SystemAccountServiceImpl implements SystemAccountService {
	
	@Autowired
	private AccountMapper accountMapper;
	
	private String directory;
	
	public SystemAccountServiceImpl(@Value("${file.upload.directory}") String directory) {
		this.directory = directory;
	}

	@Override
	public PageInfo<Account> list(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Account> list = accountMapper.selectList();
		return new PageInfo<Account>(list);
	}
	
	@Override
	public Optional<Account> findById(int aid) {
		return Optional.ofNullable(accountMapper.selectByPrimaryKey(aid));
	}
	
	@Override
	public Account insert(Account account) throws NoSuchAlgorithmException {
		String defaultImgPath = directory + "/images/default/img-001.png";
		String secretkey = PasswordEncryption.convertSecretKeyToString(PasswordEncryption.generateSecretKey("HmacSHA256", 256));
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", PasswordEncryption.generatePasswordEncoder(secretkey));
		String encodedPassword = passwordEncoder.encode(account.getPassword());
		
		account.setPassword(encodedPassword);
		account.setSecretkey(secretkey);
		account.setDateCreated(LocalDateTime.now());
		account.setAccountStatus((byte) 1);
		account.setImgPath(defaultImgPath);

		accountMapper.insertSelective(account);
		return account;
	}

	@Override
	public Account updateListPassword(Account account) throws NoSuchAlgorithmException {
		String secretkey = PasswordEncryption.convertSecretKeyToString(PasswordEncryption.generateSecretKey("HmacSHA256", 256));
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", PasswordEncryption.generatePasswordEncoder(secretkey));
		String encodedPassword = passwordEncoder.encode(account.getPassword());
		
		account.setPassword(encodedPassword);
		account.setSecretkey(secretkey);
		account.setDateModified(LocalDateTime.now());
		
		accountMapper.updateByPrimaryKeySelective(account);
		
		return account;
	}
	
	@Override
	public Account update(Account account) {
		account.setDateModified(LocalDateTime.now());
		
		accountMapper.updateByPrimaryKeySelective(account);
		
		return account;
	}
	
	@Override
	public Integer delete(int aid) {
		return accountMapper.deleteByPrimaryKey(aid);
	}

	@Override
	public Account getByUsername(String username) {
		return accountMapper.selectByUsername(username);
	}
	
	@Override
	public Account updateImagePath(Account account) {
		account.setDateModified(LocalDateTime.now());
		
		accountMapper.updateImagePathByUsername(account);
		
		return account;
	}

//	@Override
//	public Integer countAccount() {
//		// TODO Auto-generated method stub
//		return accountMapper.countAccount();
//	}

//	@Override
//	public Integer countAccountByStatus() {
//		// TODO Auto-generated method stub
//		return accountMapper.countAccountbyStatus();
//	}
}
