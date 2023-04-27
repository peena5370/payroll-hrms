package com.company.payroll.service.impl;

import org.springframework.stereotype.Service;

import com.company.payroll.mapper.AvatarMapper;
import com.company.payroll.model.ProfileImage;
import com.company.payroll.service.AvatarService;

@Service
public class AvatarServiceImpl implements AvatarService {
	
	private final AvatarMapper avatarMapper;
	
	public AvatarServiceImpl(AvatarMapper avatarMapper) {
		this.avatarMapper = avatarMapper;
	}

	@Override
	public ProfileImage loadAvatar(Integer sapid) {
		return avatarMapper.selectAvatar(sapid);
	}

	@Override
	public Integer saveAvatar(ProfileImage avatar) {
		return avatarMapper.insertAvatar(avatar);
	}

	@Override
	public Integer updateAvatar(ProfileImage avatar) {
		return avatarMapper.updateAvatar(avatar);
	}

}
