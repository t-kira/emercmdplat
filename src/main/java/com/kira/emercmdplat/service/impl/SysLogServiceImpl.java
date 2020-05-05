package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.SysLogMapper;
import com.kira.emercmdplat.pojo.SysLog;
import com.kira.emercmdplat.pojo.SysLogExtend;
import com.kira.emercmdplat.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/4 23:03
 * @Description:
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper slm;

    @Override
    public int insert(SysLog sysLog) {
        return slm.insert(sysLog);
    }

    @Override
    public boolean delete(Long id) {
        return slm.delete(id);
    }

    @Override
    public boolean update(SysLog sysLog) {
        return slm.update(sysLog);
    }

    @Override
    public SysLog selectById(Long id) {
        return slm.selectById(id);
    }

    @Override
    public List<SysLog> queryForAll(SysLogExtend sysLogExtend) {
        return slm.queryForAll(sysLogExtend);
    }

    @Override
    public Long queryForCounts(SysLogExtend sysLogExtend) {
        return slm.queryForCounts(sysLogExtend);
    }

    @Override
    public List<SysLog> queryForPage(SysLogExtend sysLogExtend) {
        return slm.queryForPage(sysLogExtend);
    }
}
