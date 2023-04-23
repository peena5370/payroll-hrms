package com.company.payroll.controller.api;

import java.time.LocalDate;
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

import com.company.payroll.model.Manager;
import com.company.payroll.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	@GetMapping("/list")
	public List<Manager> listManager() {
		return managerService.listManager();
	}
	
	@GetMapping("/list/information/{id}")
	public Manager getInfoById(@PathVariable("id")int mid) {
		return managerService.getInfoById(mid);
	}
	
	@GetMapping("/profile/name/{username}")
	public Manager getInfoByUsername(@PathVariable("username")String username) {
		return managerService.getInfoByUsername(username);
	}
	
	@GetMapping("/profile/{id}")
	public Manager getInfoBySapId(@PathVariable("id")int sapid) {
		return managerService.getInfoBySapId(sapid);
	}
	
	@GetMapping("/list/count/all")
	public Integer countManager() {
		return managerService.countManager();
	}
	
	@GetMapping("/list/count/active")
	public Integer countAvailableManager() {
		return managerService.countAvailableManager();
	}
	
	@PostMapping("/insert")
	public Integer insertManager(@RequestBody Manager manager) {
		int sapid = managerService.getManagerSapId();
		String fullname = manager.getFullname();
		String role = manager.getRole();
		String gender = manager.getGender();
		LocalDate dateOfBirth = manager.getDateOfBirth();
		int age = manager.getAge();
		String martialStatus = manager.getMartialStatus();
		String education = manager.getEducation();
		String address = manager.getAddress();
		String state = manager.getState();
		String country = manager.getCountry();
		String phone = manager.getPhone();
		String email = manager.getEmail();
		LocalDate dateHired = manager.getDateHired();
		int aid = manager.getAId();
		int deptno = manager.getDeptno();
		int titleno = manager.getTitleno();
		
		Manager mgr = new Manager(sapid, fullname, role, gender, dateOfBirth, age, martialStatus, education, address, state, country, phone, email, dateHired, aid, deptno, titleno);
		
		return managerService.insertManager(mgr);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer updateManagerById(@PathVariable("id")int sapid, @RequestBody Manager manager) {
		String fullname = manager.getFullname();
		String gender = manager.getGender();
		LocalDate dateOfBirth = manager.getDateOfBirth();
		int age = manager.getAge();
		String martialStatus = manager.getMartialStatus();
		String education = manager.getEducation();
		String address = manager.getAddress();
		String state = manager.getState();
		String country = manager.getCountry();
		String phone = manager.getPhone();
		String email = manager.getEmail();
		int deptno = manager.getDeptno();
		int titleno = manager.getTitleno();
		
		Manager mgr = new Manager(sapid, fullname, gender, dateOfBirth, age, martialStatus, education, address, state, country, phone, email, deptno, titleno);
		
		return managerService.updateInfoById(mgr);
	}
	
	@PutMapping("/profile/{id}/update")
	public Integer updateManagerBySapId(@PathVariable("id")int sapid, @RequestBody Manager manager) {
		String fullname = manager.getFullname();
		String gender = manager.getGender();
		LocalDate dateOfBirth = manager.getDateOfBirth();
		int age = manager.getAge();
		String martialStatus = manager.getMartialStatus();
		String education = manager.getEducation();
		String address = manager.getAddress();
		String state = manager.getState();
		String country = manager.getCountry();
		String phone = manager.getPhone();
		String email = manager.getEmail();
		
		Manager mgr = new Manager(sapid, fullname, gender, dateOfBirth, age, martialStatus, education, address, state, country, phone, email);
		
		return managerService.updateInfoBySapId(mgr);
	}
	
	@PutMapping("/list/information/{id}/update-resign")
	public Integer updateResignDate(@PathVariable("id")int sapid, @RequestBody Manager manager) {
		LocalDate dateResign = manager.getDateResign();

		return managerService.updateResignDate(dateResign, sapid);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public Integer deleteManager(@PathVariable("id")int id) {
		return managerService.deleteManager(id);
	}
}
