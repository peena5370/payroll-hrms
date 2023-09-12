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
public class HmsStaffLeaning {
    private Long id;

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;

    private Byte status;

    private Long staffId;

    private Long learningDetailId;
}