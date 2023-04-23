package com.company.payroll.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Account;
import com.company.payroll.model.Employee;
import com.company.payroll.model.Manager;
import com.company.payroll.model.ResponseObject;
import com.company.payroll.service.AccountService;
import com.company.payroll.service.EmployeeService;
import com.company.payroll.service.ManagerService;
import com.company.payroll.utils.PasswordEncription;

@RestController
public class LoginController {
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/back-end/login/validate")
	public ResponseObject backendValidate(HttpSession session, @RequestBody Account account) {
		String username = account.getUsername();
		String password = account.getPassword();

		ResponseObject resp = new ResponseObject();
		
		Manager manager = managerService.getInfoByUsername(username);
		
		Account acc = accountService.getAccountByUsername(username);
		// @TODO with null pointer exception for manager.getRole() that return status code 500
		switch(manager.getRole()) {
			case "Administrator":
				Boolean validatePass1 = PasswordEncription.verifyUserPassword(password, acc.getPassword(), acc.getKey());
				if(validatePass1==true && acc.getAccountStatus()==1) {
					session.setAttribute("username", username);
					resp.setCode(200);
					resp.setMsg("");
					resp.setHttpUrl("back-end/admin/dashboard");
				} else {
					resp.setCode(101);
					resp.setMsg("Wrong username or password. Please relogin.");
					resp.setHttpUrl("");
				}
				break;
			case "Manager":
				Boolean validatePass2 = PasswordEncription.verifyUserPassword(password, acc.getPassword(), acc.getKey());
				if(validatePass2==true && acc.getAccountStatus()==1) {
					session.setAttribute("username", username);
					resp.setCode(200);
					resp.setMsg("");
					resp.setHttpUrl("back-end/manager/dashboard");
				} else {
					resp.setCode(101);
					resp.setMsg("Wrong username or password. Please relogin.");
					resp.setHttpUrl("");
				}
				break;
			default:
				break;
		}
		
		return resp;
	}
	
	@PostMapping("/home/login/validate")
	public ResponseObject homeValidate(HttpSession session, @RequestBody Employee employee) {
		int sapid = employee.getESapId();
		String password = employee.getPassword();
		
		ResponseObject resp = new ResponseObject();
		
		Employee emp = employeeService.getEmployeePasswordBySapId(sapid);

		Boolean validatePassword = PasswordEncription.verifyUserPassword(password, emp.getPassword(), emp.getKey());
		if(emp.getESapId() != 0 && validatePassword==true) {
			session.setAttribute("sapid", sapid);
			resp.setCode(200);
			resp.setMsg("");
			resp.setHttpUrl("home/dashboard");
		} else {
			resp.setCode(101);
			resp.setMsg("Wrong username or password.");
			resp.setHttpUrl("");
		}

		return resp;
	}
}
