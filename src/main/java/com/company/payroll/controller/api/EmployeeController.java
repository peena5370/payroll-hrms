package com.company.payroll.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Employee>> listEmployee() {
		return ResponseEntity.ok(employeeService.getList());
	}
	
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Employee> getById(@PathVariable("id")int eid) {
		return ResponseEntity.ok(employeeService.getById(eid));
	}

	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody Employee employee) {
		Integer status = employeeService.insert(employee);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@PathVariable("id")int eid, @RequestBody Employee employee) {
		Integer status = employeeService.update(employee);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@PathVariable("id")int eid) {
		Integer status = employeeService.delete(eid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
//	@GetMapping("/profile/{id}")
//	public Employee getEmployeeBySapId(@PathVariable("id")int sapid) {
//		return employeeService.getEmployeeBySapId(sapid);
//	}
//	
//	@GetMapping("/list/count/all")
//	public Integer countEmployee() {
//		return employeeService.countEmployee();
//	}
//	
//	@GetMapping("/list/count/active")
//	public Integer countAvailableEmployee() {
//		return employeeService.countAvailableEmployee();
//	}
	
//	@PutMapping("/profile/{id}/update/password")
//	public Integer updateProfilePassword(@PathVariable("id")int id, @RequestBody Employee employee) {
//		String password = employee.getPassword();
//		String key = PasswordEncryption.getSaltvalue(30);
//		String hash = PasswordEncryption.generateSecurePassword(password, key);
//		
//		Employee emp = new Employee(id, hash, key, LocalDateTime.now());
//		
//		return employeeService.updatePasswordBySapId(emp);
//	}
//	
//	@PutMapping("/list/information/{id}/update-resign")
//	public Integer updateResignDate(@PathVariable("id")int id, @RequestBody Employee employee) {
//		LocalDate dateResign = employee.getDateResign();
//		
//		return employeeService.updateResignDateBySapId(id, dateResign);
//	}
	
//	@PutMapping("/profile/{id}/update")
//	public Integer updateEmployeeProfile(@PathVariable("id")int id, @RequestBody Employee employee) {
//		String fullname = employee.getFullname();
//		String gender = employee.getGender();
//		LocalDate dateOfBirth = employee.getDateOfBirth();
//		int age = employee.getAge();
//		String martialStatus = employee.getMartialStatus();
//		String education = employee.getEducation();
//		String address = employee.getAddress();
//		String state = employee.getState();
//		String country = employee.getCountry();
//		String phone = employee.getPhone();
//		String email = employee.getEmail();
//		
//		Employee emp = new Employee(id, fullname, gender, dateOfBirth, age, martialStatus, education, address, state, country, phone ,email);
//		
//		return employeeService.updateInfoByEmployee(emp);
//	}

}
