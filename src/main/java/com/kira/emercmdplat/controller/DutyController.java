package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.*;
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
        Map<String, Object> map = new HashMap<>();
        List<Shift> list = ss.queryShiftForPage(shift);
        Long count = ss.queryShiftForCounts(shift);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }

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
    @PostMapping(name = "新增排班", value = "shift_detail_add")
    public AlvesJSONResult insertShiftDetail(@Validated @RequestBody ShiftDetail shiftDetail) {
        int result = ss.insertShiftDetail(shiftDetail);
        if (result > 0) {
            return AlvesJSONResult.ok();
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "排班失败");
        }
    }
    @GetMapping(name = "删除排班", value = "shift_detail_delete/{shiftDetailId}")
    public AlvesJSONResult insertShiftDetail(@PathVariable Long shiftDetailId) {
        boolean result = ss.deleteShiftDetail(shiftDetailId);
        if (result) {
            return AlvesJSONResult.ok();
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "删除排班信息");
        }
    }
}
