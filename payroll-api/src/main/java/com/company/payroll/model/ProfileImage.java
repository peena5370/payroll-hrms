package com.company.payroll.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProfileImage {
	private int id;
	private String realName;
	private String newName;
	private String filePath;
	private long fileSize;
	private String fileType;
	private LocalDate updateDate;
	private Integer sapid;
	
	public ProfileImage(String realName, String newName, String filePath, long fileSize, String fileType, LocalDate updateDate, Integer sapid) {
		this.realName = realName;
		this.newName = newName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.updateDate = updateDate;
		this.sapid = sapid;
	}
}
