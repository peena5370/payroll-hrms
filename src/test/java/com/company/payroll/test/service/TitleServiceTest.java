package com.company.payroll.test.service;

//import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.company.payroll.mapper.TitleMapper;
import com.company.payroll.model.Title;
import com.company.payroll.service.impl.TitleServiceImpl;

@ExtendWith(MockitoExtension.class)
class TitleServiceTest {
	@Mock
	private TitleMapper titleMapper;
	
	@InjectMocks
	private TitleServiceImpl titleService;
	
	@BeforeEach
	void setup() {
		Title title = new Title();
		title.setTitleno(1);
		title.setTitlename("Demo title name");
		title.setTitledesc("Demo title description");
	}
	
	@Test
	void getAllTitle() {
		Mockito.when(titleMapper.selectList()).thenReturn(List.of(new Title(), new Title()));
		
//		assertThat(titleService.getList());
	}
}
