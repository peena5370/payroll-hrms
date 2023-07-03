package com.company.payroll.service.impl;

import com.company.payroll.mapper.StaffLeaveDetailsMapper;
import com.company.payroll.model.StaffLeaveDetails;
import com.company.payroll.service.StaffLeaveDetailsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffLeaveDetailsServiceImpl implements StaffLeaveDetailsService {

    @Autowired
    private StaffLeaveDetailsMapper staffLeaveDetailsMapper;

    @Override
    public PageInfo<StaffLeaveDetails> list(int page, int offset) {
        PageHelper.startPage(page, offset);
        return new PageInfo<StaffLeaveDetails>(staffLeaveDetailsMapper.selectList());
    }

    @Override
    public StaffLeaveDetails findById(Integer ldId) {
        return staffLeaveDetailsMapper.selectByPrimaryKey(ldId);
    }

    @Override
    public StaffLeaveDetails update(StaffLeaveDetails staffLeaveDetails) {
        staffLeaveDetailsMapper.updateByPrimaryKeySelective(staffLeaveDetails);
        return staffLeaveDetails;
    }
}
