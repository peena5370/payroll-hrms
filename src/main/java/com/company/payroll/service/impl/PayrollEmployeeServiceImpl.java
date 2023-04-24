package com.company.payroll.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.payroll.mapper.PayrollEmployeeMapper;
import com.company.payroll.model.PayrollEmployee;
import com.company.payroll.service.PayrollEmployeeService;

@Service
public class PayrollEmployeeServiceImpl implements PayrollEmployeeService {

	private PayrollEmployeeMapper payrollEmployeeMapper;
	
	public PayrollEmployeeServiceImpl(PayrollEmployeeMapper payrollEmployeeMapper) {
		this.payrollEmployeeMapper = payrollEmployeeMapper;
	}
	
	@Override
	public List<PayrollEmployee> getList() {
		return payrollEmployeeMapper.selectList();
	}

	@Override
	public List<PayrollEmployee> getListByEId(int eid) {
		return payrollEmployeeMapper.selectListByEId(eid);
	}
	
	@Override
	public PayrollEmployee getById(int prid) {
		return payrollEmployeeMapper.selectByPrimaryKey(prid);
	}
	@Override
	public Integer insert(PayrollEmployee payroll) {
		return payrollEmployeeMapper.insertSelective(payroll);
	}

	@Override
	public Integer update(PayrollEmployee payroll) {
		return payrollEmployeeMapper.updateByPrimaryKeySelective(payroll);
	}

	@Override
	public Integer delete(int prid) {
		return payrollEmployeeMapper.deleteByPrimaryKey(prid);
	}

//	@Override
//	public List<Payroll> listPaymentDateAndSapId(int esapid) {
//		// TODO Auto-generated method stub
//		return payrollMapper.selectpaymentDatebySapId(esapid);
//	}
//	
//	@Override
//	public Payroll getPayslip(int esapid, LocalDate paymentdate) {
//		// TODO Auto-generated method stub
//		return payrollMapper.selectPayslipbyDateAndSapId(esapid, paymentdate);
//	}
}
