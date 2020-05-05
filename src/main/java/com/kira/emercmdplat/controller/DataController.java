package com.kira.emercmdplat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kira.emercmdplat.pojo.DataType;
import com.kira.emercmdplat.pojo.EType;
import com.kira.emercmdplat.pojo.Mechanism;
import com.kira.emercmdplat.service.DataTypeService;
import com.kira.emercmdplat.service.MechanismService;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;

/**
 * @Author: kira
 * @Date: 2020/2/2 10:53
 * @Description:
 */
@Api2Doc(id = "data", name = "数据字典接口", order = 3)
@RestController
@RequestMapping("/data")
public class DataController {
	
	@Autowired
	private DataTypeService dataTypeService;
	@Autowired
	private MechanismService mechanismService;
	
	@Api2Doc(order = 1)
    @ApiComment(value="获取类型")
	@RequestMapping(name="获取类型",value="/getType",method=RequestMethod.GET)
	public List<EType> getType(@ApiComment(value="数据类型id",sample="1") Integer dataId) {
		List<EType> list = dataTypeService.queryTypeListByDataId(dataId);
		return list;
	}
	
	@Api2Doc(order = 2)
    @ApiComment(value="获取单位")
	@RequestMapping(name="获取单位",value="/getMechanism",method=RequestMethod.GET)
	public List<Mechanism> getMechanism() {
		return mechanismService.queryForAll(null);
	}
	
	@Api2Doc(order = 3)
    @ApiComment(value="获取物资来源类型")
	@RequestMapping(name="获取物资来源类型",value="/getResourceTypes",method=RequestMethod.GET)
	public List<DataType> getResourceTypes() {
		DataType d = new DataType();
		d.setType(1);
		return dataTypeService.queryForAll(d);
	}
    
	@Api2Doc(order = 4)
    @ApiComment(value="获取存储点列表")
	@RequestMapping(name="获取存储点列表",value="/getResourcesByType",method=RequestMethod.GET)
	public List<DataType> getResourcesByType(@ApiComment("物资来源类型id") int id) {
		return dataTypeService.getResourcesByType(id);
	}
}
