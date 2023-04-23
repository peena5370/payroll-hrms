package com.company.payroll.controller.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Payroll;
import com.company.payroll.service.PayrollService;
import com.company.payroll.utils.DateTimeUtils;

//@RestController
//@RequestMapping("/payroll")
public class PayrollController {
	
	@Autowired
	private PayrollService payrollService;
	
	@GetMapping("/list")
	public List<Payroll> listPayroll() {
		return payrollService.listPayroll();
	}
	
	@GetMapping("/list/information/{id}")
	public Payroll getPayrollById(@PathVariable("id")int id) {
		return payrollService.getPayrollById(id);
	}
	
	@GetMapping("/list/information/date/{id}")
	public List<Payroll> getPayrollDateBySapId(@PathVariable("id")int sapid) {
		return payrollService.listPaymentDateAndSapId(sapid);
	}
	
	@GetMapping("/employee/{date}/{id}")
	public Payroll getPaySlipBySapId(@PathVariable("id")int sapid, @PathVariable("date")String date) {
		
		return payrollService.getPayslip(sapid, DateTimeUtils.stringToFormatDate(date));
	}
	
	@PostMapping("/insert")
	public Integer insertPayroll(@RequestBody Payroll payroll) {
		return payrollService.insertPayroll(payroll);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer updatePayroll(@PathVariable("id")int id, @RequestBody Payroll payroll) {
		Double basicPay = payroll.getBasicPay();
		Double overtimePay = payroll.getOvertimePay();
		Double allowance = payroll.getAllowance();
		Double transport = payroll.getTransport();
		Double otherDeduction = payroll.getOtherDeduction();
		Double employeeEpf = payroll.getEmployeeEpf();
		Double employeeSocso = payroll.getEmployeeSocso();
		Double employeeEis = payroll.getEmployeeEis();
		Double employerEpf = payroll.getEmployerEpf();
		Double employerSocso = payroll.getEmployerSocso();
		Double employerEis = payroll.getEmployerEis();
		Double mtdPcb = payroll.getMtdPcb();
		Double totalPay = payroll.getTotalPay();
		String payPeriod = payroll.getPayPeriod();
		LocalDate paymentDate = payroll.getPaymentDate();
		int eid = payroll.getEId();
		
		Payroll pr = new Payroll(id, basicPay, overtimePay, allowance, transport, otherDeduction, employeeEpf, employeeSocso, employeeEis, employerEpf, employerSocso, 
										employerEis, mtdPcb, totalPay, payPeriod, paymentDate, eid);
		
		return payrollService.updatePayrollById(pr);
	}
}
