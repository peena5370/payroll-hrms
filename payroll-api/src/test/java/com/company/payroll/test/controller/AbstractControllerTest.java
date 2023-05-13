package com.company.payroll.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;

public abstract class AbstractControllerTest {
	
	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	protected void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	protected String toJsonString(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}
	
	protected <T> T mapFromJson (String json, Class<T> clazz) 
			throws IOException, JsonProcessingException {
		return objectMapper.readValue(json, clazz);
	}
	
	protected void createWhenSuccess() throws Exception {}
	

	protected void createWhenFailed() throws Exception {}
	
	protected void queryAll() throws Exception {}
	
	protected void queryById() throws Exception {}
	
	protected void updateWhenSuccess() throws Exception {}
	
	protected void updateWhenFailed() throws Exception {}
	
	protected void deleteWhenSuccess() throws Exception {}
	
	protected void deleteWhenFailed() throws Exception {}
}
