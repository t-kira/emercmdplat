package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.MedicalInstitution;
import com.kira.emercmdplat.service.MedicalInstitutionService;
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
@RequestMapping("/medicalInstitution")
public class MedicalInstitutionController extends BaseController {

    @Autowired
    private MedicalInstitutionService medicalInstitutionService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(MedicalInstitution medicalInstitution) {
        medicalInstitutionService.insert(medicalInstitution);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/update")
    public AlvesJSONResult update(MedicalInstitution medicalInstitution) {
        medicalInstitutionService.update(medicalInstitution);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/delete")
    public AlvesJSONResult delete(MedicalInstitution medicalInstitution) {
        medicalInstitutionService.delete(medicalInstitution);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
        MedicalInstitution medicalInstitution = medicalInstitutionService.selectById(id);
        return AlvesJSONResult.ok(medicalInstitution);
    }

    @RequestMapping("/list")
    public AlvesJSONResult list(MedicalInstitution medicalInstitution, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<MedicalInstitution> list = medicalInstitutionService.queryForPage(medicalInstitution, page, pageSize);
        Long count = medicalInstitutionService.queryForCounts(medicalInstitution);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
