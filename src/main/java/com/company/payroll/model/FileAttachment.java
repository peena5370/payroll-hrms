package com.company.payroll.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FileAttachment {
	private Integer fId;
	
	private String fileName;
	
	private long fileSize;
	
	@JsonProperty("path")
	private String attachmentPath;
	
	private Integer mId;
	
	private Integer eId;
}
