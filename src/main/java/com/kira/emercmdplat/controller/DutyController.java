package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.JobService;
import com.kira.emercmdplat.service.MechanismService;
import com.kira.emercmdplat.service.ShiftService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/7/9 16:30
 * @Description:值班管理
 */
@CrossOrigin
@RestController
@RequestMapping("/duty")
public class DutyController extends BaseController {

    @Autowired
    private ShiftService ss;
    @Autowired
    private MechanismService ms;
    @Autowired
    private JobService js;

    @ResponseBody
    @PostMapping(name = "添加班次信息", value = "shift_add")
    public AlvesJSONResult insertShift(@Validated @RequestBody Shift shift) {
        int result = ss.insertShift(shift);
        if (result > 0) {
            return AlvesJSONResult.ok();
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "添加班次失败");
        }
    }
    @ResponseBody
    @GetMapping(name = "删除班次信息", value = "shift_delete/{shiftId}")
    public AlvesJSONResult deleteShift(@PathVariable Long shiftId) {
        Shift shift = ss.selectByShiftId(shiftId);
        if (shift != null) {
            return AlvesJSONResult.errorMsg("该班次已在使用,不能删除!");
        } else {
            boolean result = ss.deleteShift(shiftId);
            if (result) {
                return AlvesJSONResult.ok();
            } else {
                throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "删除班次信息失败");
            }
        }
    }
    @ResponseBody
    @PostMapping(name = "修改班次信息", value = "shift_update")
    public AlvesJSONResult updateShift(@RequestBody Shift shift) {
        boolean result = ss.updateShift(shift);
        if (result) {
            return AlvesJSONResult.ok();
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "修改班次信息失败");
        }
    }
    @ResponseBody
    @GetMapping(name = "查看班次信息", value = "shift/{shiftId}")
    public AlvesJSONResult shift(@PathVariable Long shiftId) {
        Shift shift = ss.selectByShiftId(shiftId);
        if (shift == null)
            throw new CustomException(ResultEnum.NON_DATA.getNo(), "要修改的班次不存在");
        return AlvesJSONResult.ok(shift);
    }
    @ResponseBody
    @PostMapping(name = "查询班次列表", value = "list")
    public AlvesJSONResult list(@RequestBody Shift shift) {
        List<Shift> list = ss.queryShiftForAllOrPage(shift);
        Long count = ss.queryShiftForCounts(shift);
        return AlvesJSONResult.pageOk(list, count);
    }
    @ResponseBody
    @GetMapping("list_shift_detail/{shiftDate}/{toDay}")
    public AlvesJSONResult listShiftDetail(@PathVariable String shiftDate, @PathVariable Integer toDay) {
        Map<String,String> paramMap = new HashMap<>();
        String dateFormat = "%Y-%m";
        paramMap.put("dateStr", shiftDate);
        if (toDay > 0) {
            dateFormat = "%Y-%m-%d";
        }
        paramMap.put("dateFormat", dateFormat);
        List<ShiftDetailResult> list = ss.queryDetail(paramMap);
        return AlvesJSONResult.ok(list);
    }
    @ResponseBody
    @PostMapping(name = "新增排班", value = "shift_detail_add")
    public AlvesJSONResult insertShiftDetail(@Validated @RequestBody ShiftDetail shiftDetail) {
        int result = ss.insertShiftDetail(shiftDetail);
        if (result > 0) {
            return AlvesJSONResult.ok();
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "排班失败");
        }
    }
    @ResponseBody
    @GetMapping(name = "删除排班", value = "shift_detail_delete/{shiftDetailId}")
    public AlvesJSONResult insertShiftDetail(@PathVariable Long shiftDetailId) {
        boolean result = ss.deleteShiftDetail(shiftDetailId);
        if (result) {
            return AlvesJSONResult.ok();
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "删除排班信息");
        }
    }
    @ResponseBody
    @PostMapping(name = "新增机构", value = "mechanism_add")
    public AlvesJSONResult insertMechanism(@Validated @RequestBody Mechanism mechanism) {
        int result = ms.insert(mechanism);
        if (result > 0) {
            return AlvesJSONResult.ok("机构添加成功");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "机构添加失败");
        }
    }
    @ResponseBody
    @GetMapping(name = "删除机构信息", value = "mechanism_delete/{mechanismId}")
    public AlvesJSONResult deleteMechanism(@PathVariable Long mechanismId) {
        boolean result = ms.delete(mechanismId);
        if (result) {
            return AlvesJSONResult.ok("机构删除成功");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "机构删除失败");
        }
    }
    @ResponseBody
    @GetMapping(name = "查看机构详情", value = "mechanism/{mechanismId}")
    public AlvesJSONResult mechanism(@PathVariable Long mechanismId) {
        Mechanism mechanism = ms.selectById(mechanismId);
        return AlvesJSONResult.ok(mechanism);
    }
    @ResponseBody
    @PostMapping(name = "修改机构信息", value = "mechanism_update")
    public AlvesJSONResult updateMechanism(@RequestBody Mechanism mechanism) {
        boolean result = ms.update(mechanism);
        if (result) {
            return AlvesJSONResult.ok("机构修改成功");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "机构修改失败");
        }
    }
    @ResponseBody
    @PostMapping(name = "查询机构列表", value = "list_mechanism")
    public AlvesJSONResult mechanismList(@RequestBody(required = false) Mechanism mechanism) {
        List<Mechanism> list = ms.queryForAllOrPage(mechanism);
        Long count = ms.queryForCounts(mechanism);
        return AlvesJSONResult.pageOk(list, count);
    }
    @ResponseBody
    @PostMapping(name = "新增职位", value = "job_add")
    public AlvesJSONResult insertJob(@Validated @RequestBody Job job) {
        int result = js.insert(job);
        if (result > 0) {
            return AlvesJSONResult.ok("职位添加成功");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "职位添加失败");
        }
    }
    @ResponseBody
    @GetMapping(name = "删除职位信息", value = "job_delete/{jobId}")
    public AlvesJSONResult deleteJob(@PathVariable Long jobId) {
        boolean result = js.delete(jobId);
        if (result) {
            return AlvesJSONResult.ok("职位删除成功");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "职位删除失败");
        }
    }
    @ResponseBody
    @GetMapping(name = "查看职位详情", value = "job/{jobId}")
    public AlvesJSONResult job(@PathVariable Long jobId) {
        Job job = js.selectById(jobId);
        return AlvesJSONResult.ok(job);
    }
    @ResponseBody
    @PostMapping(name = "修改职位信息", value = "job_update")
    public AlvesJSONResult updateJob(@RequestBody Job job) {
        boolean result = js.update(job);
        if (result) {
            return AlvesJSONResult.ok("职位修改成功");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "职位修改失败");
        }
    }
    @ResponseBody
    @PostMapping(name = "查询职位列表", value = "list_job")
    public AlvesJSONResult jobList(@RequestBody(required = false) Job job) {
        List<Job> list = js.queryForAllOrPage(job);
        Long count = js.queryForCounts(job);
        return AlvesJSONResult.pageOk(list, count);
    }
}
