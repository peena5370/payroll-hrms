package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.PmsPayroll;

public interface PmsPayrollService {
	
	public int createPayroll(PmsPayroll payroll);
	
	public int updatePayroll(PmsPayroll payroll);
	
	public PmsPayroll getPayrollById(Long id);
	
	public List<PmsPayroll> getAllPayroll();
	
	public int deletePayrollById(Long id);
}
