package com.company.payroll.mapper;

import java.util.List;

import com.company.payroll.model.StaffPromotion;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffPromotionMapper {
    int deleteByPrimaryKey(Integer pId);
    int insert(StaffPromotion row);
    int insertSelective(StaffPromotion row);
    StaffPromotion selectByPrimaryKey(Integer pId);
    List<StaffPromotion> selectList();
    List<StaffPromotion> selectListByStaffId(Integer staffId);
    int updateByPrimaryKey(StaffPromotion row);
    int updateByPrimaryKeySelective(StaffPromotion row);
}