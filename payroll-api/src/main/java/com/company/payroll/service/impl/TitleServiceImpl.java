package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.TitleMapper;
import com.company.payroll.model.Title;
import com.company.payroll.service.TitleService;

@Service
public class TitleServiceImpl implements TitleService {

	@Autowired
	private TitleMapper titleMapper;
	
	@Override
	public List<Title> getList() {
		return titleMapper.selectList();
	}

	@Override
	public Title getByTitleno(int titleno) {
		return titleMapper.selectByPrimaryKey(titleno);
	}

	@Override
	public Integer insert(Title title) {
		return titleMapper.insertSelective(title);
	}

	@Override
	public Integer update(Title title) {
		return titleMapper.updateByPrimaryKeySelective(title);
	}

	@Override
	public Integer delete(int titleno) {
		return titleMapper.deleteByPrimaryKey(titleno);
	}
}
