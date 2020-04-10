package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.ReserveLibrary;
import com.kira.emercmdplat.service.ReserveLibraryService;
import com.kira.emercmdplat.utils.AlvesJSONResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:03
 * @Description:
 */
@RestController
@RequestMapping("/reserveLibrary")
public class ReserveLibraryController extends BaseController {

    @Autowired
    private ReserveLibraryService reserveLibraryService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(ReserveLibrary reserveLibrary) {
        reserveLibraryService.insert(reserveLibrary);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/update")
    public AlvesJSONResult update(ReserveLibrary reserveLibrary) {
        reserveLibraryService.update(reserveLibrary);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/delete")
    public AlvesJSONResult delete(ReserveLibrary reserveLibrary) {
        reserveLibraryService.delete(reserveLibrary);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
        ReserveLibrary reserveLibrary = reserveLibraryService.selectById(id);
        return AlvesJSONResult.ok(reserveLibrary);
    }

    @RequestMapping("/list")
    public AlvesJSONResult list(ReserveLibrary reserveLibrary, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<ReserveLibrary> list = reserveLibraryService.queryForPage(reserveLibrary, page, pageSize);
        Long count = reserveLibraryService.queryForCounts(reserveLibrary);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
