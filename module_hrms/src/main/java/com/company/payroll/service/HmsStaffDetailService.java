package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.HmsStaffBanking;
import com.company.payroll.model.HmsStaffDetail;
import com.company.payroll.model.HmsStaffJobTitle;
import com.company.payroll.model.HmsStaffLeaveBalance;
import com.company.payroll.model.HmsStaffSalary;
import com.github.pagehelper.PageInfo;

public interface HmsStaffDetailService {
	
	PageInfo<HmsStaffBanking> getAllStaffbanking(int page, int offset);
	
	Optional<HmsStaffBanking> getStaffBankingById(Long id);
	
	Integer insertStaffBanking(HmsStaffBanking hmsStaffBanking);
	
	Integer updateStaffBanking(HmsStaffBanking hmsStaffBanking);
	
	Integer deleteStaffBankingById(Long id);
	
	PageInfo<HmsStaffDetail> getAllStaffDetail(int page, int offset);
	
	Optional<HmsStaffDetail> getStaffDetailById(Long id);
	
	Optional<HmsStaffDetail> getStaffDetailByStaffId(Long staffId);
	
	Integer insertStaffDetail(HmsStaffDetail hmsStaffDetail);
	
	Integer updateStaffDetail(HmsStaffDetail hmsStaffDetail);
	
	Integer deleteStaffDetailById(Long id);
	
	PageInfo<HmsStaffJobTitle> getAllJobTitle(int page, int offset);
	
	Optional<HmsStaffJobTitle> getJobTitleById(Long id);
	
	Integer insertJobTitle(HmsStaffJobTitle hmsStaffJobTitle);
	
	Integer updateJobTitle(HmsStaffJobTitle hmsStaffJobTitle);
	
	Integer deleteJobTitleById(Long id);
	
	Optional<HmsStaffLeaveBalance> getStaffLeaveBalanceById(Long id);
	
	Integer insertStaffLeaveBalance(HmsStaffLeaveBalance hmsStaffLeaveBalance);
	
	Integer updateStaffLeaveBalance(HmsStaffLeaveBalance hmsStaffLeaveBalance);
	
	Integer deleteStaffLeaveBalanceById(Long id);

	PageInfo<HmsStaffSalary> getAllStaffSalary(int page, int offset);
	
	Optional<HmsStaffSalary> getStaffSalaryById(Long id);
	
	Integer insertStaffSalary(HmsStaffSalary hmsStaffSalary);
	
	Integer updateStaffSalary(HmsStaffSalary hmsStaffSalary);
	
	Integer deleteStaffSalaryById(Long id);
}
