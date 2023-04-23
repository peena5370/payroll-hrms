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

import com.company.payroll.model.Promotion;
import com.company.payroll.service.PromotionService;

@RestController
@RequestMapping("/promotion")
public class PromotionController {
	
	@Autowired
	private PromotionService promotionService;
	
	@GetMapping("/list")
	public List<Promotion> listPromotion() {
		return promotionService.listPromotion();
	}
	
	@GetMapping("/list/information/{id}")
	public Promotion getPromotionById(@PathVariable("id")int id) {
		return promotionService.getPromotionById(id);
	}
	
	@PostMapping("/insert")
	public Integer insertPromotion(@RequestBody Promotion promotion) {
		return promotionService.insertPromotion(promotion);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer updatePromotion(@PathVariable("id")int id, @RequestBody Promotion promotion) {
		Double currentSalary = promotion.getCurrentSalary();
		Double promoteSalary = promotion.getPromoteSalary();
		LocalDate promoteDate = promotion.getPromoteDate();
		int titleno = promotion.getTitleno();
		String comment = promotion.getComment();
		
		Promotion promo = new Promotion(id, currentSalary, promoteSalary, promoteDate, titleno, comment);
		
		return promotionService.updatePromotion(promo);
	}
}
