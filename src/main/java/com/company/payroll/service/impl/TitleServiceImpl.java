package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.TitleMapper;
import com.company.payroll.model.Title;
import com.company.payroll.service.TitleService;

//@Service
public class TitleServiceImpl implements TitleService {

	@Autowired
	private TitleMapper titleMapper;
	
	@Override
	public List<Title> listTitle() {
		// TODO Auto-generated method stub
		return titleMapper.selectTitleList();
	}

	@Override
	public List<Title> listTitlenoAndName() {
		// TODO Auto-generated method stub
		return titleMapper.selectTitleName();
	}

	@Override
	public Title getInfoByTitleno(int titleno) {
		// TODO Auto-generated method stub
		return titleMapper.selectTitlebyTitleno(titleno);
	}

	@Override
	public Integer countTitle() {
		// TODO Auto-generated method stub
		return titleMapper.countTitle();
	}

	@Override
	public Integer insertTitle(String titlename, String titledesc) {
		// TODO Auto-generated method stub
		return titleMapper.insertTitle(titlename, titledesc);
	}

	@Override
	public Integer updateTitle(Title title) {
		// TODO Auto-generated method stub
		return titleMapper.updateTitlebyTitleno(title);
	}

	@Override
	public Integer deleteTitle(int titleno) {
		// TODO Auto-generated method stub
		return titleMapper.deleteTitle(titleno);
	}

}
