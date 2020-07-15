package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.QuickReportService;
import com.kira.emercmdplat.service.ReportService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private ContactService cs;

    @ResponseBody
    @PostMapping(value = "add")
    public AlvesJSONResult insert(@Validated @RequestBody Report report, HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cs.findByToken(token);
        report.setContactId(contactsResult.getId());
        int result = rs.insert(report);
        if (result > 0) {
            return AlvesJSONResult.ok("新增成功");
        } else {
            return AlvesJSONResult.errorMsg("新增失败");
        }
    }

    /**
     * 删除
     * @param reportId
     * @return
     */
    @ResponseBody
    @GetMapping(value = "delete/{reportId}")
    public AlvesJSONResult delete(@PathVariable Long reportId) {
        boolean result = rs.delete(reportId);
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
    @GetMapping(value = "report/{reportId}")
    public AlvesJSONResult selectById(@PathVariable Long reportId) {
        Report report = rs.selectById(reportId);
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
