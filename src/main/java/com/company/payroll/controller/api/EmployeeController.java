package com.company.payroll.controller.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Employee;
import com.company.payroll.service.EmployeeService;
import com.company.payroll.utils.PasswordEncryption;

//@RestController
//@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list")
	public List<Employee> listEmploye() {
		return employeeService.listEmployee();
	}
	
	@GetMapping("/list/information/{id}")
	public Employee getEmployeeById(@PathVariable("id")int id) {
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping("/profile/{id}")
	public Employee getEmployeeBySapId(@PathVariable("id")int sapid) {
		return employeeService.getEmployeeBySapId(sapid);
	}
	
	@GetMapping("/list/count/all")
	public Integer countEmployee() {
		return employeeService.countEmployee();
	}
	
	@GetMapping("/list/count/active")
	public Integer countAvailableEmployee() {
		return employeeService.countAvailableEmployee();
	}
	
	@PostMapping("/insert")
	public Integer insertEmployee(@RequestBody Employee employee) {
		int sapid = employeeService.getEmployeeSapId();
		String fullname = employee.getFullname();
		String gender = employee.getGender();
		LocalDate dateOfBirth = employee.getDateOfBirth();
		int age = employee.getAge();
		String martialStatus = employee.getMartialStatus();
		String education = employee.getEducation();
		String address = employee.getAddress();
		String state = employee.getState();
		String country = employee.getCountry();
		String phone = employee.getPhone();
		String email = employee.getEmail();
		LocalDate dateHired = employee.getDateHired();
		int aid = employee.getAId();
		int titleno = employee.getTitleno();
		int deptno = employee.getDeptno();
		int mid = employee.getMId();

		Employee emp = new Employee(sapid, fullname, gender, dateOfBirth, age, martialStatus, education, address, state, country, phone ,email, dateHired, aid, deptno, titleno, mid);

		return employeeService.insertEmployee(emp);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer updateEmployeeByManager(@PathVariable("id")int id, @RequestBody Employee employee) {
		String fullname = employee.getFullname();
		String gender = employee.getGender();
		LocalDate dateOfBirth = employee.getDateOfBirth();
		int age = employee.getAge();
		String martialStatus = employee.getMartialStatus();
		String education = employee.getEducation();
		String address = employee.getAddress();
		String state = employee.getState();
		String country = employee.getCountry();
		String phone = employee.getPhone();
		String email = employee.getEmail();
		int titleno = employee.getTitleno();
		int deptno = employee.getDeptno();
		
		Employee emp = new Employee(id, fullname, gender, dateOfBirth, age, martialStatus, education, address, state, country, phone ,email, deptno, titleno);
		
		return employeeService.updateInfoByManager(emp);
	}
	
	@PutMapping("/profile/{id}/update")
	public Integer updateEmployeeProfile(@PathVariable("id")int id, @RequestBody Employee employee) {
		String fullname = employee.getFullname();
		String gender = employee.getGender();
		LocalDate dateOfBirth = employee.getDateOfBirth();
		int age = employee.getAge();
		String martialStatus = employee.getMartialStatus();
		String education = employee.getEducation();
		String address = employee.getAddress();
		String state = employee.getState();
		String country = employee.getCountry();
		String phone = employee.getPhone();
		String email = employee.getEmail();
		
		Employee emp = new Employee(id, fullname, gender, dateOfBirth, age, martialStatus, education, address, state, country, phone ,email);
		
		return employeeService.updateInfoByEmployee(emp);
	}
	
	@PutMapping("/profile/{id}/update/password")
	public Integer updateProfilePassword(@PathVariable("id")int id, @RequestBody Employee employee) {
		String password = employee.getPassword();
		String key = PasswordEncryption.getSaltvalue(30);
		String hash = PasswordEncryption.generateSecurePassword(password, key);
		
		Employee emp = new Employee(id, hash, key, LocalDateTime.now());
		
		return employeeService.updatePasswordBySapId(emp);
	}
	
	@PutMapping("/list/information/{id}/update-resign")
	public Integer updateResignDate(@PathVariable("id")int id, @RequestBody Employee employee) {
		LocalDate dateResign = employee.getDateResign();
		
		return employeeService.updateResignDateBySapId(id, dateResign);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public Integer deleteEmployee(@PathVariable("id")int id) {
		return employeeService.deleteEmployee(id);
	}
}
