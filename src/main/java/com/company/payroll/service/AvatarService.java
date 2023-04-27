package com.company.payroll.service;

import com.company.payroll.model.ProfileImage;

public interface AvatarService {

	public ProfileImage loadAvatar(Integer sapid);
	
	public Integer saveAvatar(ProfileImage avatar);
	
	public Integer updateAvatar(ProfileImage avatar);
}
