package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.QuickReportService;
import com.kira.emercmdplat.service.ReportService;
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
@RequestMapping("/report")
public class ReportController extends BaseController {

    @Autowired
    private ReportService rs;

    @ResponseBody
    @PostMapping(value = "add")
    public AlvesJSONResult insert(@RequestBody Report report) {
        int result = rs.insert(report);
        if (result > 0) {
            return AlvesJSONResult.ok("");
        } else {
            return AlvesJSONResult.errorMsg("fail insert...");
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping(value = "delete/{id}")
    public AlvesJSONResult delete(@PathVariable Long id) {
        boolean result = rs.delete(id);
        if (result) {
            return AlvesJSONResult.ok("ok delete quick_report");
        } else {
            return AlvesJSONResult.errorMsg("fail delete quick_report");
        }
    }

    @ResponseBody
    @PostMapping(value = "update")
    public AlvesJSONResult update(@RequestBody Report report) {
        boolean result = rs.update(report);
        if (result) {
            return AlvesJSONResult.ok("ok update quick report...");
        } else {
            return AlvesJSONResult.errorMsg("error update quick report...");
        }
    }

    @ResponseBody
    @GetMapping(value = "report/{id}")
    public AlvesJSONResult selectById(@PathVariable Long id) {
        Report report = rs.selectById(id);
        return AlvesJSONResult.ok(report);
    }

    @ResponseBody
    @PostMapping("list")
    public AlvesJSONResult list(@RequestBody Report report) {
        Map<String, Object> map = new HashMap<>();
        List<ReportResult> list = rs.queryForAllOrPage(report);
        Long count = rs.queryForCounts(report);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
