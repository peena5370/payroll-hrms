package com.company.payroll.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.PayrollMapper;
import com.company.payroll.model.Payroll;
import com.company.payroll.service.PayrollService;

@Service
public class PayrollServiceImpl implements PayrollService {

	@Autowired
	private PayrollMapper payrollMapper;
	
	@Override
	public List<Payroll> listPayroll() {
		// TODO Auto-generated method stub
		return payrollMapper.selectPayroll();
	}

	@Override
	public List<Payroll> listPaymentDateAndSapId(int esapid) {
		// TODO Auto-generated method stub
		return payrollMapper.selectpaymentDatebySapId(esapid);
	}

	@Override
	public Payroll getPayrollById(int prid) {
		// TODO Auto-generated method stub
		return payrollMapper.selectPayrollbyId(prid);
	}

	@Override
	public Payroll getPayslip(int esapid, LocalDate paymentdate) {
		// TODO Auto-generated method stub
		return payrollMapper.selectPayslipbyDateAndSapId(esapid, paymentdate);
	}

	@Override
	public Integer insertPayroll(Payroll payroll) {
		// TODO Auto-generated method stub
		return payrollMapper.insertPayroll(payroll);
	}

	@Override
	public Integer updatePayrollById(Payroll payroll) {
		// TODO Auto-generated method stub
		return payrollMapper.updatePayrollbyId(payroll);
	}

}
