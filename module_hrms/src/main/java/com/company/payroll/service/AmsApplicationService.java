package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.AmsLoan;
import com.company.payroll.model.AmsResignation;
import com.company.payroll.model.AmsTimeOff;
import com.github.pagehelper.PageInfo;

public interface AmsApplicationService {
	
	PageInfo<AmsLoan> getAllLoan(int page, int offset);

	Optional<AmsLoan> getLoanById(Long id);
	
	Integer insertLoan(AmsLoan amsLoan);
	
	Integer updateLoan(AmsLoan amsLoan);
	
	Integer deleteLoanById(Long id);
	
	PageInfo<AmsResignation> getAllResignation(int page, int offset);
	
	Optional<AmsResignation> getResignationById(Long id);
	
	Integer insertResignation(AmsResignation amsResignation);
	
	Integer updateResignation(AmsResignation amsResignation);
	
	Integer deleteResignationById(Long id);
	
	PageInfo<AmsTimeOff> getAllTimeOff(int page, int offset);
	
	Optional<AmsTimeOff> getTimeOffById(Long id);
	
	Integer insertTimeOff(AmsTimeOff amsTimeOff);
	
	Integer updateTimeOff(AmsTimeOff amsTimeOff);
	
	Integer deleteTimeOffById(Long id);
}
