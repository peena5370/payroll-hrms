package com.company.payroll.promotion.service.impl;

import com.company.payroll.promotion.dto.EmployeePromotionDTO;
import com.company.payroll.promotion.dto.EmployeePromotionDetailDTO;
import com.company.payroll.promotion.repository.EmployeePromotionRepository;
import com.company.payroll.promotion.service.EmployeePromotionService;
import com.company.payroll.util.util.SnowFlakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeePromotionServiceImpl implements EmployeePromotionService {

    private static final String CLASS_NAME = "[EmployeePromotionServiceImpl]";

    private final SnowFlakeIdGenerator snowFlakeIdGenerator;
    private final EmployeePromotionRepository employeePromotionRepository;

    public EmployeePromotionServiceImpl(SnowFlakeIdGenerator snowFlakeIdGenerator,
                                        EmployeePromotionRepository employeePromotionRepository) {
        this.snowFlakeIdGenerator = snowFlakeIdGenerator;
        this.employeePromotionRepository = employeePromotionRepository;
    }

    @Override
    public int createPromotionDetail(EmployeePromotionDTO employeePromotionDTO) {
        return 0;
    }

    @Override
    public List<EmployeePromotionDetailDTO> getAllPromotionsByOffsetAndLimitOrEmployeeId(Long employeeId, int offset, int limit) {
        return List.of();
    }

    @Override
    public Optional<EmployeePromotionDetailDTO> getPromotionDetailById(long promotionId) {
        return Optional.empty();
    }

    @Override
    public int updatePromotionDetailById(long promotionId, EmployeePromotionDTO employeePromotionDTO) {
        return 0;
    }

    @Override
    public int deletePromotionDetailById(long promotionId) {
        return 0;
    }
}
