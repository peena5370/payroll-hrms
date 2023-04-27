package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.ProfileImage;

@Repository
public interface AvatarMapper {
	
	ProfileImage selectAvatar(Integer sapid);
	
	Integer insertAvatar(ProfileImage avatar);
	
	Integer updateAvatar(ProfileImage avatar);
}
