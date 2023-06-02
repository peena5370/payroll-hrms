package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Manager;

@Repository
public interface ManagerMapper {
	int countActiveDepartmentManager(Integer deptno);
	
    int deleteByPrimaryKey(Integer mId);

    int insert(Manager row);

    int insertSelective(Manager row);
    
    List<Manager> selectList();

    Manager selectByPrimaryKey(Integer mId);
    
    Manager selectByResignId(Integer resignId);

    int updateByPrimaryKeySelective(Manager row);

    int updateByPrimaryKey(Manager row);
}