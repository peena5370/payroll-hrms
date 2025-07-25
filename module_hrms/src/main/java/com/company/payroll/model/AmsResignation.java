package com.company.payroll.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AmsResignation {
    private Long id;

    private String reason;

    private LocalDate dateResign;

    private Byte status;

    private String fileName;

    private Long fileSize;

    private String filePath;

    private Long staffId;
}