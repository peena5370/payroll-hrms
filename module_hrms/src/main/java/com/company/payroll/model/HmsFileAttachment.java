package com.company.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class HmsFileAttachment {
    private Long id;

    private String fileName;

    private Long fileSize;

    private String filePath;

    private Long staffId;
}