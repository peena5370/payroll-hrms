package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.HmsStaffJobTitle;

public interface HmsStaffDetailService {
	
	public int createJobTitle(HmsStaffJobTitle jobTitle);

	public HmsStaffJobTitle getJobTitle(Long id);
	
	public int updateJobTitle(HmsStaffJobTitle jobTitle);
	
	public int deleteJobTitle(Long id);
	
	public List<HmsStaffJobTitle> getAllJobTitle();
}
