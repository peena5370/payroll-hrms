package com.company.payroll.controller.api;

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

import com.company.payroll.model.Title;
import com.company.payroll.service.TitleService;

@RestController
@RequestMapping("/title")
public class TitleController {
	
	@Autowired
	private TitleService titleService;
	
	@GetMapping("/list")
	public List<Title> listBaseTitle() {
		return titleService.listTitle();
	}
	
	@GetMapping("/list/drop-down")
	public List<Title> listTitlenoAndName() {
		return titleService.listTitlenoAndName();
	}
	
	@GetMapping("/list/information/{id}")
	public Title getInfoByTitleno(@PathVariable("id")int id) {
		return titleService.getInfoByTitleno(id);
	}
	
	@GetMapping("/list/count/all")
	public Integer countTitle() {
		return titleService.countTitle();
	}
	
	@PostMapping("/insert")
	public Integer insertTitle(@RequestBody Title title) {
		String titleName = title.getTitleName();
		String titleDesc = title.getTitleDesc();
		
		return titleService.insertTitle(titleName, titleDesc);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer updateTitle(@PathVariable("id")int id, @RequestBody Title title) {
		String titleName = title.getTitleName();
		String titleDesc = title.getTitleDesc();
		
		Title ttl = new Title(id, titleName, titleDesc);
		
		return titleService.updateTitle(ttl);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public Integer deleteTitle(@PathVariable("id")int id) {
		return titleService.deleteTitle(id);
	}
}
