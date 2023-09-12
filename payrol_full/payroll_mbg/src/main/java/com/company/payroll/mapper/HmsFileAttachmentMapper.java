package com.company.payroll.mapper;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.HmsFileAttachment;

@Repository
public interface HmsFileAttachmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HmsFileAttachment row);

    int insertSelective(HmsFileAttachment row);

    HmsFileAttachment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HmsFileAttachment row);

    int updateByPrimaryKey(HmsFileAttachment row);
}