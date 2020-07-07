package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.SysLog;
import com.kira.emercmdplat.pojo.SysLogExtend;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/4 23:02
 * @Description:
 */
public interface SysLogService {

    int insert(SysLog sysLog);

    boolean delete(Long id);

    boolean update(SysLog sysLog);

    SysLog selectById(Long id);

    List<SysLog> queryForAll(SysLogExtend sysLogExtend);

    Long queryForCounts(SysLogExtend sysLogExtend);

    List<SysLog> queryForPage(SysLogExtend sysLogExtend);

    List<JSONObject> selectByEventId(Long eventId);
}
