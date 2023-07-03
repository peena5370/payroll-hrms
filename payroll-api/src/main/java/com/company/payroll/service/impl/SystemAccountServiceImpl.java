package com.company.payroll.service.impl;

import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import com.company.payroll.model.SystemAccount;
import com.company.payroll.util.FileUtils;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.AccountMapper;
import com.company.payroll.model.Account;
import com.company.payroll.service.SystemAccountService;
import com.company.payroll.util.PasswordEncryption;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SystemAccountServiceImpl implements SystemAccountService {
	
	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private FileUtils fileUtils;

	@Value("${file.upload.directory}")
	private String directory;


	@Override
	public PageInfo<SystemAccount> list(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<SystemAccount>(accountMapper.selectList());
	}
	
	@Override
	public Optional<SystemAccount> findById(Integer aId) {
		return Optional.ofNullable(accountMapper.selectByPrimaryKey(aId));
	}

	@Override
	public Optional<SystemAccount> findByUsername(String username) {
		return Optional.ofNullable(accountMapper.selectByUsername(username));
	}

	@Override
	public SystemAccount insert(SystemAccount systemAccount) throws NoSuchAlgorithmException {
		String defaultPath = this.directory + "/account/default_img.png";
		String secretKey = PasswordEncryption.convertSecretKeyToString(PasswordEncryption.generateSecretKey("HmacSHA256", 256));
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", PasswordEncryption.generatePasswordEncoder(secretKey));
		String encodedPassword = passwordEncoder.encode(systemAccount.getPassword());

		systemAccount.setPassword(encodedPassword);
		systemAccount.setSecretKey(secretKey);
		systemAccount.setDateCreated(LocalDateTime.now());
		systemAccount.setLastAttempt((byte) 0);
		systemAccount.setAccountStatus((byte) 1);
		systemAccount.setImgPath(defaultPath);

		accountMapper.insertSelective(systemAccount);

		return systemAccount;
	}

	@Override
	public SystemAccount updateListPassword(SystemAccount systemAccount) throws NoSuchAlgorithmException {
		String secretKey = PasswordEncryption.convertSecretKeyToString(PasswordEncryption.generateSecretKey("HmacSHA256", 256));
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", PasswordEncryption.generatePasswordEncoder(secretKey));
		String encodedPassword = passwordEncoder.encode(systemAccount.getPassword());

		systemAccount.setPassword(encodedPassword);
		systemAccount.setSecretKey(secretKey);
		systemAccount.setDateModified(LocalDateTime.now());

		accountMapper.updateByPrimaryKeySelective(systemAccount);

		return systemAccount;
	}

	@Override
	public Account update(Account account) {
		return null;
	}

	@Override
	public SystemAccount modifyStatusRoles(SystemAccount systemAccount) {
		systemAccount.setDateModified(LocalDateTime.now());

		accountMapper.updateByPrimaryKeySelective(systemAccount);

		return systemAccount;
	}

	@Override
	public Integer delete(Integer aId) {
		int row = 0;
		SystemAccount systemAccount = accountMapper.selectByPrimaryKey(aId);
		if(systemAccount.getImgPath().equals(this.directory + "/account/default_img.png")) {
			row += accountMapper.deleteByPrimaryKey(aId);
		} else {
			boolean bool = fileUtils.delete(Paths.get(systemAccount.getImgPath()));
			if(bool) {
				row += accountMapper.deleteByPrimaryKey(aId);
			}
		}

		return row;
	}

	@Override
	public Account getByUsername(String username) {
		return null;
	}
	
	@Override
	public Account updateImagePath(Account account) {
		return null;
	}

	@Override
	public SystemAccount updateImagePath(MultipartFile image, SystemAccount systemAccount) {
		String filePath = "/account/" + systemAccount.getUsername();
		String contentType = image.getContentType();
		if(Objects.equals(contentType, "image/jpeg") || Objects.equals(contentType, "image/png")
				|| Objects.equals(contentType, "image/gif")) {
			systemAccount.setImgPath(fileUtils.imageUpload(image, filePath));
			systemAccount.setDateModified(LocalDateTime.now());

			accountMapper.updateImagePathByUsername(systemAccount);
		} else {
			throw new InvalidFileNameException(image.getOriginalFilename(), "Invalid file format");
		}

		return systemAccount;
	}

	@Override
	public SystemAccount setLastAttempt(SystemAccount systemAccount) {
		accountMapper.updateByPrimaryKeySelective(systemAccount);

		return systemAccount;
	}

	@Override
	public SystemAccount modifyPassword(SystemAccount systemAccount) throws NoSuchAlgorithmException {
		String secretKey = PasswordEncryption.convertSecretKeyToString(PasswordEncryption.generateSecretKey("HmacSHA256", 256));
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", PasswordEncryption.generatePasswordEncoder(secretKey));
		String encodedPassword = passwordEncoder.encode(systemAccount.getPassword());

		systemAccount.setPassword(encodedPassword);
		systemAccount.setSecretKey(secretKey);
		systemAccount.setDateModified(LocalDateTime.now());

		accountMapper.updatePasswordByUsername(systemAccount);

		return systemAccount;
	}

	@Override
	public Resource downloadAccountImage(String username) {
		SystemAccount systemAccount = accountMapper.selectByUsername(username);

		return fileUtils.download(Paths.get(systemAccount.getImgPath()));
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
