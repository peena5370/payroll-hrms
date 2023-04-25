package com.company.payroll.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

//@Controller
//@RequestMapping("/home")
public class HomeInterfaceController {
	
	@GetMapping("/login")
	public String MainLogin() {
		return "/view/home/index";
	}
	
	@GetMapping("/logout")
	public String MainLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/home/login";
	}
	
	@GetMapping("/dashboard")
	public String MainDashboard() {
		return "/view/home/dashboard";
	}
	
	@GetMapping("/payslip")
	public String Payslip() {
		return "/view/home/payslip";
	}
	
	@GetMapping("/leave")
	public String MainLeave() {
		return "/view/home/leave";
	}
	
	@GetMapping("/loan")
	public String MainLoan() {
		return "/view/home/loan";
	}
	
	@GetMapping("/training")
	public String MainTraining() {
		return "/view/home/training";
	}
}
