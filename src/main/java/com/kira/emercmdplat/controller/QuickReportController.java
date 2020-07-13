package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.QuickReportService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/13 03:55
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/quick")
public class QuickReportController extends BaseController {

    @Autowired
    private QuickReportService qrs;

    @ResponseBody
    @PostMapping(value = "add")
    public AlvesJSONResult insert(@RequestBody QuickReport quickReport) {
        quickReport.setOrigin(0);
        int result = qrs.insert(quickReport);
        if (result > 0) {
            return AlvesJSONResult.ok("");
        } else {
            return AlvesJSONResult.errorMsg("fail insert...");
        }
    }

    /**
     * 删除要情速报
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping(value = "delete/{id}")
    public AlvesJSONResult delete(@PathVariable Long id) {
        boolean result = qrs.delete(id);
        if (result) {
            return AlvesJSONResult.ok("ok delete quick_report");
        } else {
            return AlvesJSONResult.errorMsg("fail delete quick_report");
        }
    }

    @ResponseBody
    @PostMapping(value = "update")
    public AlvesJSONResult update(@RequestBody QuickReport quickReport) {
        boolean result = qrs.update(quickReport);
        if (result) {
            return AlvesJSONResult.ok("ok update quick report...");
        } else {
            return AlvesJSONResult.errorMsg("error update quick report...");
        }
    }

    @ResponseBody
    @GetMapping(value = "quick/{id}")
    public AlvesJSONResult selectById(@PathVariable Long id) {
        QuickReport quickReport = qrs.selectById(id);
        return AlvesJSONResult.ok(quickReport);
    }

    @ResponseBody
    @PostMapping("list")
    public AlvesJSONResult list(@RequestBody QuickReportExtend quickReportExtend) {
        Map<String, Object> map = new HashMap<>();
        List<QuickReportResult> list = qrs.queryForPage(quickReportExtend);
        Long count = qrs.queryForCounts(quickReportExtend);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
