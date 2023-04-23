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

import com.company.payroll.model.Salary;
import com.company.payroll.service.SalaryService;

@RestController
@RequestMapping("/salary")
public class SalaryController {
	
	@Autowired
	private SalaryService salaryService;
	
	@GetMapping("/list")
	public List<Salary> listSalary() {
		return salaryService.listSalary();
	}
	
	@GetMapping("/list/information/{id}")
	public Salary getSalaryInfoBySapId(@PathVariable("id")int sapid) {
		return salaryService.getInfoBySapId(sapid);
	}
	
	@PostMapping("/insert")
	public Integer insertSalary(@RequestBody Salary salary) {
		return salaryService.insertSalary(salary);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer updateSalary(@PathVariable("id")int id, @RequestBody Salary salary) {
		Double monthlySalary = salary.getMonthlySalary();
		Double annualSalary = salary.getAnnualSalary();
		LocalDate dateUpdate = salary.getDateUpdate();
		int eid = salary.getEId();
		
		Salary slr = new Salary(id, monthlySalary, annualSalary, dateUpdate, eid);
		
		return salaryService.updateSalary(slr);
	}

}
