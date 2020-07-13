package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.enums.EventProcess;
import com.kira.emercmdplat.enums.EventVerifyStatus;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.enums.SysLogType;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.mapper.ContactMapper;
import com.kira.emercmdplat.mapper.EventMapper;
import com.kira.emercmdplat.mapper.PlanTypeMapper;
import com.kira.emercmdplat.mapper.SysLogMapper;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.EventService;
import com.kira.emercmdplat.utils.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:47
 * @Description:事件service
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper em;
    @Autowired
    private ContactMapper cm;
    @Autowired
    private SysLogMapper slm;
    @Autowired
    private PlanTypeMapper ptm;

    @Transactional
    @Override
    public int insert(EventDomain eventDomain, HttpServletRequest request) {
        Event event = eventDomain.getEvent();
        List<EventParam> eventParamList = eventDomain.getEventParamList();
        String preEventNumber = DateUtil.getNowStr("yyyyMMdd");
        EventExtend eventExtend = new EventExtend();
        eventExtend.setOrder("e_id");
        eventExtend.setOrderType("desc");
        eventExtend.setEventNumber(preEventNumber);
        List<EventResult> eventResults = em.queryEventNumberForAll(eventExtend);
        if (eventResults != null && eventResults.size() > 0) {
            EventResult eventResult = eventResults.get(0);
            String eventNumber = eventResult.getEventNumber();
            event.setEventNumber(preEventNumber + StringUtil.genEventNumber(eventNumber));
        } else {
            event.setEventNumber(preEventNumber + "00001");
        }
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cm.findByToken(token);
        event.setContactId(contactsResult.getId());
        event.setVerifyStatus(EventVerifyStatus.UN_VERIFY.getNo());
        event.setProcess(EventProcess.EVENT_RECEIVE.getNo());
        event.setReceiveTime(DateUtil.getNowStr("yyy-MM-dd HH:mm:ss"));
        int result = em.insert(event);
        if (result > 0) {
            if (eventParamList != null && eventParamList.size() > 0) {
                for (EventParam eventParam : eventParamList) {
                    if (eventParam != null) {
                        eventParam.setEventId(event.getId());
                        eventParam.setEventNumber(event.getEventNumber());
                        em.insertParam(eventParam);
                    }
                }
            }
            return 1;
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "事件保存失败");
        }
    }

    @Override
    public boolean delete(Event event) {
        return em.delete(event);
    }

    @Override
    @Transactional
    public boolean update(EventDomain eventDomain, HttpServletRequest request) {
        Event event = eventDomain.getEvent();
        List<EventParam> eventParamList = eventDomain.getEventParamList();
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cm.findByToken(token);
        event.setContactId(contactsResult.getId());
        boolean result = em.update(event);
        if (result) {
            List<EventParamResult> paramList =  em.selectParamByEventId(event.getId());
            if (paramList != null && paramList.size() > 0) {
                for (EventParamResult param : paramList) {
                    em.deleteParam(param.getId());
                }
            }
            if (eventParamList != null && eventParamList.size() > 0) {
                for (EventParam eventParam : eventParamList) {
                    if (eventParam != null) {
                        eventParam.setEventId(event.getId());
                        eventParam.setEventNumber(event.getEventNumber());
                        em.insertParam(eventParam);
                    }
                }
            }
            return true;
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "事件更新失败");
        }
    }

    @Override
    public boolean update(Event event) {
        return em.update(event);
    }

    @Override
    public EventResult selectById(Long id) {
        return em.selectById(id);
    }

    @Override
    public List<EventResult> queryForAll(EventExtend eventExtend) {
        return em.queryForAll(eventExtend);
    }

    @Override
    public List<EventResult> queryForPage(EventExtend eventExtend) {
        eventExtend.setPage((eventExtend.getPage() - 1) * eventExtend.getPageSize());
        return em.queryForPage(eventExtend);
    }

    @Override
    public Long queryForCounts(EventExtend pojo) {
        return em.queryForCounts(pojo);
    }

    @Override
    public int insertParam(EventParam eventParam) {
        return em.insertParam(eventParam);
    }

    @Override
    public int deleteParam(Long id) {
        return em.deleteParam(id);
    }

    @Override
    public List<EventParamResult> selectParamByEventId(Long eventId) {
        return em.selectParamByEventId(eventId);
    }

    @Override
    public List<EventResult> queryByTitle(Event event) {
        return em.queryByTitle(event);
    }

    @Override
    public boolean mergeEvent(EventResult coverEvent, HttpServletRequest request, VerifyEventReq eventReq, EventResult mainEvent) {
        //被合并
        coverEvent.setVerifyStatus(EventVerifyStatus.IS_MERGE.getNo());
        em.update(coverEvent);
        //被合并的事件记录
        String token = TokenUtil.getRequestToken(request);
        Contacts contacts = cm.findByToken(token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("verifyEventReq", eventReq);
        jsonObject.put("eventDesc", coverEvent.getEventDesc());
        SysLog sysLog = new SysLog();
        sysLog.setSysLogType(SysLogType.EVENT_MERGE.getNo());
        sysLog.setEventId(mainEvent.getId());
        sysLog.setOperation(SysLogType.EVENT_MERGE.getName());
        sysLog.setUserName(contacts.getContactName());
        sysLog.setCreateTime(coverEvent.getReceiveTime());
        sysLog.setParams(jsonObject.toString());
        int result = slm.insert(sysLog);
        if (result > 0) {
            return true;
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "合并事件失败");
        }
    }

    @Override
    public Map<String, Object> statistics() {
        Map<String, Object> statisticsMap = new HashMap<>();
        Map<String, Integer> eventLevelMap =  em.countEventLevel();
        List<Map<String, Object>> ptIdMapList = em.countEventPtId();

        List<Map<String, Object>> parentMapList = new ArrayList<>();
        //根据ptId统计事件数量
        for (Map<String, Object> map : ptIdMapList) {
            Long ptId = StringUtil.toLongDefValue(map.get("ptId").toString(), 0l);
            Integer count = StringUtil.toIntDefValue(map.get("count").toString(), 0);
            //查询ptId的顶级父节点数据
            Map<String, Object> parentMap = em.selectParentId(ptId);
            parentMap.put("count", count);
            parentMapList.add(parentMap);
        }
        //相同的ptId 数量累加
        for(int i = 0; i < parentMapList.size(); i++) {
            Map<String, Object> map = parentMapList.get(i);
            long ptId = StringUtil.toLongDefValue(map.get("ptId").toString(), 0l);
            int count = StringUtil.toIntDefValue(map.get("count").toString(), 0);
            for(int j = i + 1; j < parentMapList.size(); j++) {
                Map<String, Object> _map = parentMapList.get(j);
                long _ptId = StringUtil.toLongDefValue(_map.get("ptId").toString(), 0l);
                int _count = StringUtil.toIntDefValue(_map.get("count").toString(), 0);
                if (ptId == _ptId) {
                    map.put("count", (count + _count));
                    parentMapList.remove(j);
                    j--;
                }
            }
        }
        statisticsMap.put("eventLevelMap", eventLevelMap);
        statisticsMap.put("eventPtIdList", parentMapList);
        return statisticsMap;
    }
}
