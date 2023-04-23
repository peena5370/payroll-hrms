package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.PromotionMapper;
import com.company.payroll.model.Promotion;
import com.company.payroll.service.PromotionService;

//@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionMapper promotionMapper;
	
	@Override
	public List<Promotion> listPromotion() {
		// TODO Auto-generated method stub
		return promotionMapper.selectPromotion();
	}

	@Override
	public Promotion getPromotionById(int pid) {
		// TODO Auto-generated method stub
		return promotionMapper.selectPromotionbyId(pid);
	}

	@Override
	public Integer insertPromotion(Promotion promotion) {
		// TODO Auto-generated method stub
		return promotionMapper.insertPromotion(promotion);
	}

	@Override
	public Integer updatePromotion(Promotion promotion) {
		// TODO Auto-generated method stub
		return promotionMapper.updatePromotion(promotion);
	}

}
