package com.company.payroll.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.TitleMapper;
import com.company.payroll.model.Title;
import com.company.payroll.service.TitleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TitleServiceImpl implements TitleService {

	@Autowired
	private TitleMapper titleMapper;
	
	@Override
	public PageInfo<Title> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Title> list = titleMapper.selectList();
		return new PageInfo<Title>(list);
	}

	@Override
	public Optional<Title> getByTitleno(int titleno) {
		return Optional.ofNullable(titleMapper.selectByPrimaryKey(titleno));
	}

	@Override
	public Title insert(Title title) {
		titleMapper.insert(title);
		return title;
	}

	@Override
	public Title update(Title title) {
		titleMapper.updateByPrimaryKeySelective(title);
		return title;
	}

	@Override
	public Integer delete(int titleno) {
		return titleMapper.deleteByPrimaryKey(titleno);
	}
}
