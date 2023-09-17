package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.PmsPayroll;
import com.github.pagehelper.PageInfo;

public interface PmsPayrollService {
	
	PageInfo<PmsPayroll> getAllPayroll(int page, int offset);

	PageInfo<PmsPayroll> getAllPayrollByStaffId(Long staffId, int page, int offset);
	
	Optional<PmsPayroll> getPayrollById(Long id);
	
	Integer insertPayroll(PmsPayroll pmsPayroll);
	
	Integer updatePayroll(PmsPayroll pmsPayroll);
	
	Integer deletePayrollById(Long id);
	
}
