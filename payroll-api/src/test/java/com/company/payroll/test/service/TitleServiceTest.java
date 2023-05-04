package com.company.payroll.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import com.company.payroll.model.Title;
import com.company.payroll.service.TitleService;

@Transactional
@ExtendWith(MockitoExtension.class)
public class TitleServiceTest {
	
	@Mock
	public TitleService titleService;
	
	@Test
	@DisplayName("Insert title")
	void create() {
		Title title = new Title(1, "Title 1", "Title description");
		
		when(titleService.insert(any(Title.class))).thenReturn(1);
		
		int status = titleService.insert(title);
		
		assertEquals(status, 1);
	}
	
	@Test
	@DisplayName("Get all list")
	void readAll() {
		Title title1 = new Title(1, "Title 1", "Title description");		
		Title title2 = new Title(2, "Title 2", "Title description 2");
		
		when(titleService.getList()).thenReturn(Lists.newArrayList(title1, title2));
		
		List<Title> list = titleService.getList();
		
		assertTrue(list.size()==2);
	}
	
	@Test
	@DisplayName("Get title by id")
	void readById() {
		Title title = new Title(1, "Title 1", "Title description");
		
		when(titleService.getByTitleno(title.getTitleno())).thenReturn(title);
		
		Title title2 = titleService.getByTitleno(title.getTitleno());
		
		assertEquals(title2.getTitlename(), title.getTitlename());
	}
	
	@Test
	@DisplayName("Update title")
	void update() {
		Title newtitle = new Title(1, "title", "new description");
		when(titleService.update(any(Title.class))).thenReturn(1);
		
		int status = titleService.update(newtitle);
		
		assertEquals(status, 1);
	}
	
	@Test
	@DisplayName("Delete title")
	void delete() {
		Title title = new Title(1, "Title 1", "Title description");
		
		when(titleService.delete(title.getTitleno())).thenReturn(1);
		
		titleService.delete(title.getTitleno());
		title = titleService.getByTitleno(title.getTitleno());
		
		assertNull(title);
	}
}
