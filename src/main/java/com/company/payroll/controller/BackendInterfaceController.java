package com.company.payroll.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/back-end")
public class BackendInterfaceController {
	
	@GetMapping("/login")
	public String Login() {
		return "/view/back-end/index";
	}
	
	@GetMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/back-end/login";
	}
	
	@GetMapping("/admin/dashboard")
	public String AdminDashBoard() {
		return "/view/back-end/admin/dashboard";
	}
	
	@GetMapping("/admin/account")
	public String AdminAccount() {
		return "/view/back-end/admin/account";
	}
	
	@GetMapping("/admin/department")
	public String AdminDepartment() {
		return "/view/back-end/admin/department";
	}
	
	@GetMapping("/admin/employee")
	public String AdminEmployee() {
		return "/view/back-end/admin/employee";
	}
	
	@GetMapping("/admin/manager")
	public String AdminManager() {
		return "/view/back-end/admin/manager";
	}
	
	@GetMapping("/admin/profile")
	public String AdminProfile() {
		return "/view/back-end/admin/profile";
	}
	
	@GetMapping("/admin/title")
	public String AdminTitle() {
		return "/view/back-end/admin/title";
	}
	
	@GetMapping("/manager/account")
	public String ManagerAccount() {
		return "/view/back-end/manager/account";
	}
	
	@GetMapping("/manager/banking")
	public String ManagerBanking() {
		return "/view/back-end/manager/banking";
	}
	
	@GetMapping("/manager/dashboard")
	public String ManagerDashboard() {
		return "/view/back-end/manager/dashboard";
	}
	
	@GetMapping("/manager/employee")
	public String ManagerEmployee() {
		return "/view/back-end/manager/employee";
	}
	
	@GetMapping("/manager/leave")
	public String ManagerLeave() {
		return "/view/back-end/manager/leave";
	}
	
	@GetMapping("/manager/loan")
	public String ManagerLoan() {
		return "/view/back-end/manager/loan";
	}
	
	@GetMapping("/manager/payroll")
	public String ManagerPayroll() {
		return "/view/back-end/manager/payroll";
	}
	
	@GetMapping("/manager/promotion")
	public String ManagerPromotion() {
		return "/view/back-end/manager/promotion";
	}
	
	@GetMapping("/manager/resign")
	public String ManagerResign() {
		return "/view/back-end/manager/resign";
	}
	
	@GetMapping("/manager/salary")
	public String ManagerSalary() {
		return "/view/back-end/manager/salary";
	}
	
	@GetMapping("/manager/training")
	public String ManagerTraining() {
		return "/view/back-end/manager/training";
	}
}
