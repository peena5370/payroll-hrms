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

import com.company.payroll.model.Resignation;
import com.company.payroll.service.ResignationService;

@RestController
@RequestMapping("/resign")
public class ResignationController {
	
	@Autowired
	private ResignationService resignationService;
	
	@GetMapping("/list")
	public List<Resignation> listResignation() {
		return resignationService.listResignation();
	}
	
	@GetMapping("/list/information/{id}")
	public Resignation getResignInfoById(@PathVariable("id")int id) {
		return resignationService.getInfoById(id);
	}
	
	@PostMapping("/insert")
	public Integer insertResignInfo(@RequestBody Resignation resign) {
		return resignationService.insertResignInfo(resign);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer updateResignInfo(@PathVariable("id")int id, @RequestBody Resignation resign) {
		String reason = resign.getReason();
		LocalDate resignDate = resign.getResignDate();
		int resignStatus = resign.getResignStatus();
		Integer eid = resign.getEId();
		
		Resignation rs = new Resignation(id, reason, resignDate, resignStatus, eid);
		
		return resignationService.updateResignInfoById(rs);
	}
	
	@PutMapping("/list/information/{id}/update/status")
	public Integer updateResignStatus(@PathVariable("id")int id, @RequestBody Resignation resign) {
		int resignStatus = resign.getResignStatus();
		Integer mid = resign.getMId();
		
		Resignation rs = new Resignation(id, resignStatus, mid);
		
		return resignationService.updateResignStatus(rs);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public Integer deleteResignInfo(@PathVariable("id")int id) {
		return resignationService.deleteResign(id);
	}

}
