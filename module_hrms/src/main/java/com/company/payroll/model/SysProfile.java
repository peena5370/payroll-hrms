package com.company.payroll.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SysProfile {
    private Long id;

    private String aboutProfile;

    private String imagePath;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;

    private Long accountId;

    private Long staffId;
}