package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.*;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.Node;
import com.kira.emercmdplat.utils.PojoUtil;
import com.kira.emercmdplat.utils.PropertiesUtils;
import com.kira.emercmdplat.utils.file.FileResult;
import com.kira.emercmdplat.utils.file.FileuploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:49
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/event")
public class EventController extends BaseController {
    @Autowired
    private EventService es;
    @Autowired
    private PlanTypeService pts;
    @Autowired
    private DutyService ds;
    @Autowired
    private MechanismService ms;
    @Autowired
    private VerifyReportService vrs;

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
            return AlvesJSONResult.ok("insert ok");
        } else {
            return AlvesJSONResult.errorMsg("fail insert...");
        }
    }

    /**
     * 获取事件类型列表
     * @return 事件类型集合
     */
    @ResponseBody
    @GetMapping(value = "type_list")
    public AlvesJSONResult listPlanType() {
        List<Node> list = pts.listTypeTree(null);
        return AlvesJSONResult.ok(list);
    }

    /**
     * 根据事件类型返回事件参数集合
     * @param id 事件类型ID
     * @return
     */
    @ResponseBody
    @GetMapping(value = "event_param/{id}")
    public AlvesJSONResult listParam(@PathVariable int id) {
        List<PlanParam> planParamList = pts.listParamsByPtId(id);
        return AlvesJSONResult.ok(planParamList);
    }

    /**
     * 获取值班人员集合
     * @return
     */
    @ResponseBody
    @GetMapping(value = "duty_list")
    public AlvesJSONResult listDuty(){
        List<Duty> dutyList = ds.queryForAll(new Duty());
        return AlvesJSONResult.ok(dutyList);
    }

    /**
     * 获取机构集合
     * @return
     */
    @ResponseBody
    @GetMapping(value = "mechanism_list")
    public AlvesJSONResult listMechanism() {
        List<Mechanism> mechanismList = ms.queryForAll(new Mechanism());
        return AlvesJSONResult.ok(mechanismList);
    }

    /**
     * 添加核实报告内容
     * @param verifyReport
     * @return
     */
    @ResponseBody
    @PostMapping(value = "add_verify")
    public AlvesJSONResult insertVerifyReport(@PathVariable VerifyReport verifyReport) {
        int result = vrs.insert(verifyReport);
        if (result > 0) {
            return AlvesJSONResult.ok("verifyReport insert ok ...");
        } else {
            return AlvesJSONResult.errorMsg("fail insert verifyReport...");
        }
    }

    @ResponseBody
    @PostMapping(value = "upload")
    public AlvesJSONResult upload(@PathVariable MultipartFile multipartFile) {
        try {
            String path = PropertiesUtils.getInstance().getProperty("attachmentPath").toString();
            FileResult fileResult = FileuploadUtil.saveFile(multipartFile, path, "jpg");
            return AlvesJSONResult.ok(fileResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AlvesJSONResult.errorMsg("fail upload...");
    }

    @ResponseBody
    @PostMapping(value = "add_development")
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
