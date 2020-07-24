package com.kira.emercmdplat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.Vehicle;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;


@Api2Doc(id = "vehicle", name = "车辆接口", order = 6)
@RestController
@RequestMapping("/vehicle")
public class VehicleController extends BaseController {
	
	@Api2Doc(order = 5)
    @ApiComment(value="列出车辆")
    @RequestMapping(name="列出车辆",value="/list",method=RequestMethod.POST)
    public AlvesJSONResult list(@ApiComment(value="列出车辆",sample="根据id查询车辆接口可查看字段信息") @RequestBody Vehicle vehicle) {
    	Map<String,Object> result = new HashMap<>();
        List<Vehicle> list = new ArrayList<Vehicle>();
        Vehicle v1 = new Vehicle();
        v1.setId(1);
        v1.setNumber("浙A12345");
        v1.setMid(1);
        v1.setPic("张爱国");
        v1.setCellNum("13245678909");
        v1.setLng(108.286836);
        v1.setLat(22.785361);
        list.add(v1);
        Vehicle v2 = new Vehicle();
        v2.setId(2);
        v2.setNumber("浙A12346");
        v2.setMid(1);
        v2.setPic("李中华");
        v2.setCellNum("13245678909");
        v2.setLng(108.258856);
        v2.setLat(22.851342);
        list.add(v2);
        Vehicle v3 = new Vehicle();
        v3.setId(2);
        v3.setNumber("浙A12347");
        v3.setMid(1);
        v3.setPic("赵磊");
        v3.setCellNum("13245678909");
        v3.setLng(108.26452);
        v3.setLat(22.879496);
        list.add(v3);
        result.put("list", list);
        result.put("count", 3);
        return AlvesJSONResult.ok(result);
    }

}
