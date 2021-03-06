package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.config.WebSecurityConfig;
import com.kira.emercmdplat.enums.*;
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
        Event newEvent = new Event();
        newEvent.setOrder("e_id");
        newEvent.setOrderType("desc");
        newEvent.setEventNumber(preEventNumber);
        List<EventResult> eventResults = em.queryEventNumberForAll(newEvent);
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
        event.setReceiveTime(DateUtil.getNowStr());
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
        EventResult eventResult = em.selectById(event.getId());
        if (eventResult == null) {
            throw new CustomException(ResultEnum.NON_DATA.getNo());
        }
        if (eventResult.getStatus() == EventStatus.FINISH.getNo() && eventResult.getProcess() == EventProcess.EVENT_FINISH.getNo()) {
            throw new CustomException(ResultEnum.EVENT_FINISH.getNo());
        }
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
    public List<EventResult> queryForAllOrPage(Event event) {
        if (event != null && event.getPage() != null) {
            event.setPage((event.getPage() - 1) * event.getPageSize());
        }
        List<EventResult> eventResultList = em.queryForAllOrPage(event);

        List<EventResult> list = replaceIcon(eventResultList);

        return list;
    }

    protected List<EventResult> replaceIcon(List<EventResult> eventResultList) {
        if (eventResultList != null && eventResultList.size() > 0) {
            for (EventResult eventResult : eventResultList) {
                if (eventResult.getEventLevel() != null) {
                    String iconUrl = EventLevelIcon.getByValue(eventResult.getEventLevel()).getName();
                    if (iconUrl.contains(",")) {
                        String[] iconArr = iconUrl.split(",");
                        eventResult.setCommonIcon(WebSecurityConfig.HOST + iconArr[0]);
                        eventResult.setActiveIcon(WebSecurityConfig.HOST + iconArr[1]);
                    }
                }
            }
        }
        return eventResultList;
    }

    @Override
    public Long queryForCounts(Event event) {
        return em.queryForCounts(event);
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

    @Transactional
    @Override
    public boolean verifyEvent(VerifyEventReq eventReq, HttpServletRequest request) {
        EventResult coverEvent = em.selectById(eventReq.getCoverEId());
        if (coverEvent == null) {
            throw new CustomException(ResultEnum.NON_DATA.getNo());
        }
        if (coverEvent.getStatus() == EventStatus.FINISH.getNo() && coverEvent.getProcess() == EventProcess.EVENT_FINISH.getNo()) {
            throw new CustomException(ResultEnum.EVENT_FINISH.getNo());
        }
        if (eventReq.getMainEId() != null) {
            EventResult mainEvent = em.selectById(eventReq.getMainEId());
            mainEvent.setVerifyMethod(eventReq.getVerifyMethod());
            mainEvent.setVerifyStatus(eventReq.getVerifyStatus());
            mainEvent.setEventType(eventReq.getEventType());
            coverEvent.setMergeReason(eventReq.getMergeReason());
            if (eventReq.getEventType() == 1) {
                mainEvent.setProcess(EventProcess.EVENT_FINISH.getNo());
                mainEvent.setStatus(EventStatus.FINISH.getNo());
            } else {
                mainEvent.setProcess(EventProcess.VERIFY_REPORT.getNo());
            }
            //更新主事件
            em.update(mainEvent);
            //合并事件
            mergeEvent(coverEvent, request, eventReq, mainEvent);
        } else {
            coverEvent.setVerifyMethod(eventReq.getVerifyMethod());
            coverEvent.setVerifyStatus(eventReq.getVerifyStatus());
            coverEvent.setEventType(eventReq.getEventType());
            coverEvent.setMergeReason(eventReq.getMergeReason());
            if (eventReq.getVerifyStatus() == EventVerifyStatus.IS_FALSE.getNo()) {
                coverEvent.setProcess(EventProcess.EVENT_FINISH.getNo());
                coverEvent.setStatus(EventStatus.FINISH.getNo());
            }
            em.update(coverEvent);
        }
        return true;
    }

    @Transactional
    @Override
    public boolean mergeEvent(VerifyEventReq eventReq, HttpServletRequest request) {

        EventResult coverEvent = em.selectById(eventReq.getCoverEId());
        EventResult mainEvent = em.selectById(eventReq.getMainEId());
        coverEvent.setMergeReason(eventReq.getMergeReason());
        if (coverEvent == null) {
            throw new CustomException(ResultEnum.NON_DATA.getNo());
        }
        if (coverEvent.getStatus() == EventStatus.FINISH.getNo() && coverEvent.getProcess() == EventProcess.EVENT_FINISH.getNo()) {
            throw new CustomException(ResultEnum.EVENT_FINISH.getNo());
        }
        //合并事件
        return mergeEvent(coverEvent, request, eventReq, mainEvent);
    }
}
