package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.*;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/4 22:49
 * @Description:
 */
public interface SysLogMapper {

    int insert(SysLog sysLog);

    boolean delete(Long id);

    boolean update(SysLog sysLog);

    SysLog selectById(Long id);

    List<SysLog> queryForAll(SysLogExtend sysLogExtend);

    Long queryForCounts(SysLogExtend sysLogExtend);

    List<SysLog> queryForPage(SysLogExtend sysLogExtend);

    List<SysLog> selectByEid(Long eid);
}
