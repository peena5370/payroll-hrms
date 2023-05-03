package com.company.payroll.test.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.company.payroll.controller.api.FileController;
import com.company.payroll.service.AccountService;
import com.company.payroll.service.FileAttachmentService;

@WebMvcTest(value=FileController.class, 
			excludeAutoConfiguration= {SecurityAutoConfiguration.class},
			excludeFilters=@ComponentScan.Filter(type=FilterType.REGEX, pattern="com.company.payroll.*.*Filter"))
public class FileControllerTest extends AbstractControllerTest {

	@InjectMocks
	private FileController fileController;
	
	@MockBean
	private AccountService accountService;
	
	@MockBean
	private FileAttachmentService fileAttachmentService;
	
	@Before
	@Override
	protected void setUp() {
		super.setUp();
	}
	
	@Test
	void imageUploadWhenSuccess() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void imageUploadWhenFailed() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void downloadImageWhenSuccess() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void downloadImageWhenFailed() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void queryEmployeeDocumentList() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void queryManagerDocumentList() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void uploadDocumentEmployeeWhenSuccess() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void uploadDocumentEmployeeWhenFailed() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void uploadDocumentManagerWhenSuccess() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void uploadDocumentManagerWhenFailed() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void downloadDocumentWhenSuccess() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void downloadDocumentWhenFailed() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void deleteDocumentWhenSuccess() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Test
	void deleteDocumentWhenFailed() throws Exception {
		// TODO Auto-generated method stub
	}
	
}
