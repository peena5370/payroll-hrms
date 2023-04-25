package com.company.payroll.controller.api;

import java.util.List;

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
	
	private ManagerService managerService;
	
	public ManagerController(ManagerService managerService) {
		this.managerService = managerService;
	}
	
	@GetMapping("/list")
	public List<Manager> listManager() {
		return managerService.getList();
	}
	
	@GetMapping("/list/information/{id}")
	public Manager getById(@PathVariable("id")int mid) {
		return managerService.getById(mid);
	}
	
	@PostMapping("/insert")
	public Integer insert(@RequestBody Manager manager) {
		return managerService.insert(manager);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer update(@RequestBody Manager manager) {
		return managerService.update(manager);
	}

	
	@DeleteMapping("/list/information/{id}/delete")
	public Integer delete(@PathVariable("id")int id) {
		return managerService.delete(id);
	}
	
//	@PutMapping("/profile/{id}/update")
//	public Integer updateManagerBySapId(@PathVariable("id")int sapid, @RequestBody Manager manager) {
//		String fullname = manager.getFullname();
//		String gender = manager.getGender();
//		LocalDate dateOfBirth = manager.getDateOfBirth();
//		int age = manager.getAge();
//		String martialStatus = manager.getMartialStatus();
//		String education = manager.getEducation();
//		String address = manager.getAddress();
//		String state = manager.getState();
//		String country = manager.getCountry();
//		String phone = manager.getPhone();
//		String email = manager.getEmail();
//		
//		Manager mgr = new Manager(sapid, fullname, gender, dateOfBirth, age, martialStatus, education, address, state, country, phone, email);
//		
//		return managerService.updateInfoBySapId(mgr);
//	}
//	
//	@PutMapping("/list/information/{id}/update-resign")
//	public Integer updateResignDate(@PathVariable("id")int sapid, @RequestBody Manager manager) {
//		LocalDate dateResign = manager.getDateResign();
//
//		return managerService.updateResignDate(dateResign, sapid);
//	}
//	
//	@GetMapping("/profile/name/{username}")
//	public Manager getInfoByUsername(@PathVariable("username")String username) {
//		return managerService.getInfoByUsername(username);
//	}
//	
//	@GetMapping("/profile/{id}")
//	public Manager getInfoBySapId(@PathVariable("id")int sapid) {
//		return managerService.getInfoBySapId(sapid);
//	}
//	
//	@GetMapping("/list/count/all")
//	public Integer countManager() {
//		return managerService.countManager();
//	}
//	
//	@GetMapping("/list/count/active")
//	public Integer countAvailableManager() {
//		return managerService.countAvailableManager();
//	}
}
