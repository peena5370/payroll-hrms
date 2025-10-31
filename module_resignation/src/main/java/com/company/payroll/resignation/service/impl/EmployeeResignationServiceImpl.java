package com.company.payroll.resignation.service.impl;

import com.company.payroll.resignation.dto.EmployeeResignationDTO;
import com.company.payroll.resignation.dto.EmployeeResignationDetailDTO;
import com.company.payroll.resignation.repository.EmployeeResignationRepository;
import com.company.payroll.resignation.service.EmployeeResignationService;
import com.company.payroll.util.util.SnowFlakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeResignationServiceImpl implements EmployeeResignationService {
    private static final String CLASS_NAME = "[EmployeePromotionServiceImpl]";

    private final SnowFlakeIdGenerator snowFlakeIdGenerator;
    private final EmployeeResignationRepository employeeResignationRepository;

    public EmployeeResignationServiceImpl(SnowFlakeIdGenerator snowFlakeIdGenerator,
                                          EmployeeResignationRepository employeeResignationRepository) {
        this.snowFlakeIdGenerator = snowFlakeIdGenerator;
        this.employeeResignationRepository = employeeResignationRepository;
    }

    @Override
    public int createResignationInfo(EmployeeResignationDTO employeeResignationDTO) {
        return 0;
    }

    @Override
    public List<EmployeeResignationDetailDTO> getAllResignationInfoByOffsetAndLimitOrEmployeeId(Long employeeId, int offset, int limit) {
        return List.of();
    }

    @Override
    public Optional<EmployeeResignationDetailDTO> getResignationInfoById(long resignationId) {
        return Optional.empty();
    }

    @Override
    public int updateResignationInfoById(long resignationId, EmployeeResignationDTO employeeResignationDTO) {
        return 0;
    }

    @Override
    public int deleteResignationInfoById(long resignationId) {
        return 0;
    }
}
