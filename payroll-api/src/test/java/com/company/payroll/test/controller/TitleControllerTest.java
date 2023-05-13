package com.company.payroll.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.company.payroll.controller.api.TitleController;
import com.company.payroll.model.Title;
import com.company.payroll.service.TitleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@WebMvcTest(value=TitleController.class, 
			excludeAutoConfiguration= {SecurityAutoConfiguration.class},
			excludeFilters=@ComponentScan.Filter(type=FilterType.REGEX, pattern="com.company.payroll.*.*Filter"))
public class TitleControllerTest extends AbstractControllerTest {


	@InjectMocks
	private TitleController titleController;

	@MockBean
	private TitleService titleService;

	@BeforeEach
	@Override
	protected void setUp() {
		super.setUp();
	}

	@Test
	@Override
	protected void createWhenSuccess() throws Exception {
		// setup
		Title title = new Title(1, "title 1", "description demo 123.");
		
		// when condition
		when(titleService.insert(any(Title.class))).thenReturn(1);
		
		// result
		mockMvc.perform(MockMvcRequestBuilders.post("/api/title/insert")
						.content(super.toJsonString(title))
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("utf-8")
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isCreated())
						.andDo(print());
	}

	@Override
	protected void createWhenFailed() throws Exception {
		// TODO Auto-generated method stub
		super.createWhenFailed();
	}

	@Test
	@Override
	protected void queryAll() throws Exception {
		Title title1 = new Title(1, "title 1", "description demo 123.");
		Title title2 = new Title(2, "title 2", "description demo 456.");
		List<Title> list = null;
		list.add(title1);
		list.add(title2);
		PageHelper.startPage(1, 10);

		when(titleService.getListByPage(1, 10)).thenReturn(new PageInfo<Title>(list));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/title/list")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$[1].titlename").value("title 2"))
						.andExpect(jsonPath("$").isArray())
						.andExpect(jsonPath("$.length()").value(2))
						.andDo(print());
	}

	@Override
	protected void queryById() throws Exception {
		// TODO Auto-generated method stub
		super.queryById();
	}

	@Test
	@Override
	protected void updateWhenSuccess() throws Exception {
		Title title1 = new Title(1, "title 1", "description demo 123.");
		
		when(titleService.update(any())).thenReturn(1);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/title/list/information/1/update")
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("utf-8")
						.content(super.toJsonString(title1)))
						.andExpect(status().isOk())
						.andDo(print());
	}

	@Override
	protected void updateWhenFailed() throws Exception {
		// TODO Auto-generated method stub
		super.updateWhenFailed();
	}

	@Test
	@Override
	protected void deleteWhenSuccess() throws Exception {
		Title title = new Title(1, "title 1", "description demo 123.");
		
		when(titleService.delete(title.getTitleno())).thenReturn(1);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/title/list/information/1/delete")
						.param("titleno", "1")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print());
	}

	@Override
	protected void deleteWhenFailed() throws Exception {
		// TODO Auto-generated method stub
		super.deleteWhenFailed();
	}

}
