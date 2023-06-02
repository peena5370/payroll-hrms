package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Training;

@Repository
public interface TrainingMapper {
    int deleteByPrimaryKey(Integer tId);

    int insert(Training row);

    int insertSelective(Training row);
    
    List<Training> selectList();
    
    List<Training> selectListByEId(int eid);
    
    List<Training> selectListByMId(int mid);

    Training selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(Training row);

    int updateByPrimaryKeyWithBLOBs(Training row);

    int updateByPrimaryKey(Training row);
}