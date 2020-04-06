package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.Event;
import com.kira.emercmdplat.pojo.EventDevelopment;
import com.kira.emercmdplat.pojo.EventDomain;
import com.kira.emercmdplat.pojo.EventParam;
import com.kira.emercmdplat.service.EventService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:49
 * @Description:
 */
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService es;

    @ResponseBody
    @PostMapping(value = "add")
    public AlvesJSONResult insert(@RequestBody EventDomain eventDomain) {
        Event event = eventDomain.getEvent();
        List<EventParam> eventParamList = eventDomain.getEventParamList();
        int result = es.insert(event);
        if (result > 0) {
            if (eventParamList.size() > 0) {
                for (EventParam eventParam : eventParamList) {
                    eventParam.seteId(event.getId());
                    eventParam.setEventNumber(event.getEventNumber());
                    es.insertParam(eventParam);
                }
            }
            return AlvesJSONResult.ok("insert ");
        } else {
            return AlvesJSONResult.errorMsg("fail insert...");
        }
    }

    @ResponseBody
    @PostMapping(value = "addDevelopment")
    public AlvesJSONResult insertDevelopment(@RequestBody EventDevelopment development) {
        int result = es.insertDevelopment(development);
        if (result > 0) {
            return AlvesJSONResult.ok();
        } else {
            return AlvesJSONResult.errorMsg("fail insert development...");
        }
    }

    @ResponseBody
    @PostMapping(value = "updateDevelopment")
    public AlvesJSONResult updateDevelopment(@RequestBody EventDevelopment development) {
        int result = es.updateDevelopment(development);
        if (result > 0) {
            return AlvesJSONResult.ok();
        } else {
            return AlvesJSONResult.errorMsg("fail update development...");
        }
    }

    @ResponseBody
    @PostMapping(value = "remove")
    public AlvesJSONResult delete(Event event) {
        boolean result = es.delete(event);
        if (result) {
            return AlvesJSONResult.ok();
        } else {
            return AlvesJSONResult.errorMsg("fail delete...");
        }
    }

    @ResponseBody
    @PostMapping(value = "update")
    public AlvesJSONResult update(Event event) {
        boolean result = es.update(event);
        if (result) {
            return AlvesJSONResult.ok();
        } else {
            return AlvesJSONResult.errorMsg("fail update...");
        }
    }

    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
        Event event = es.selectById(id);
        return AlvesJSONResult.ok(event);
    }

    @RequestMapping("/list")
    public AlvesJSONResult list(Event event, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<Event> list = es.queryForPage(event, page, pageSize);
        Long count = es.queryForCounts(event);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
