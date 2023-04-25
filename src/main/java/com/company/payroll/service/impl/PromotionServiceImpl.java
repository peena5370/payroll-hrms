package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.payroll.mapper.PromotionMapper;
import com.company.payroll.model.Promotion;
import com.company.payroll.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	private PromotionMapper promotionMapper;
	
	public PromotionServiceImpl(PromotionMapper promotionMapper) {
		this.promotionMapper = promotionMapper;
	}
	
	@Override
	public List<Promotion> getList() {
		return promotionMapper.selectList();
	}

	@Override
	public Promotion getById(int pid) {
		return promotionMapper.selectByPrimaryKey(pid);
	}

	@Override
	public Integer insert(Promotion promotion) {
		return promotionMapper.insertSelective(promotion);
	}

	@Override
	public Integer update(Promotion promotion) {
		return promotionMapper.updateByPrimaryKeySelective(promotion);
	}

	@Override
	public Integer delete(int pid) {
		return promotionMapper.deleteByPrimaryKey(pid);
	}
}
