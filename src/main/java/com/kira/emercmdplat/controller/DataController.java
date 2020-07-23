package com.kira.emercmdplat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.BaseObject;
import com.kira.emercmdplat.pojo.DataType;
import com.kira.emercmdplat.pojo.EType;
import com.kira.emercmdplat.pojo.ETypeResult;
import com.kira.emercmdplat.service.DataTypeService;
import com.kira.emercmdplat.service.ETypeService;
import com.kira.emercmdplat.service.MechanismService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
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
	@Autowired
	private ETypeService eTypeService;

	@Api2Doc(order = 1)
    @ApiComment(value="获取类型")
	@RequestMapping(name="获取类型",value="/getType",method=RequestMethod.GET)
	public AlvesJSONResult getType(@ApiComment(value="数据类型id",sample="1") Integer dataId) {
		List<EType> list = dataTypeService.queryTypeListByDataId(dataId);
		for (EType type : list) {
			if (type.getIcon() != null) {
				type.setIcon(BaseObject.host + "/img/" + type.getIcon() + "-small.png");
			}
		}
		return AlvesJSONResult.ok(list);
	}

	@Api2Doc(order = 2)
    @ApiComment(value="获取单位")
	@RequestMapping(name="获取单位",value="/getMechanism",method=RequestMethod.GET)
	public AlvesJSONResult getMechanism() {
		return AlvesJSONResult.ok(mechanismService.queryForAllOrPage(null));
	}

	@Api2Doc(order = 3)
    @ApiComment(value="获取物资来源类型")
	@RequestMapping(name="获取物资来源类型",value="/getResourceTypes",method=RequestMethod.GET)
	public AlvesJSONResult getResourceTypes() {
		DataType d = new DataType();
		d.setType(1);
		return AlvesJSONResult.ok(dataTypeService.queryForAll(d));
	}

	@Api2Doc(order = 4)
    @ApiComment(value="获取存储点列表")
	@RequestMapping(name="获取存储点列表",value="/getResourcesByType",method=RequestMethod.GET)
	public AlvesJSONResult getResourcesByType(@ApiComment("物资来源类型id") int id) {
		return AlvesJSONResult.ok(dataTypeService.getResourcesByType(id));
	}

	@Api2Doc(order = 5)
    @ApiComment(value="查询应急资源")
	@RequestMapping(name="查询应急资源",value="/queryResources",method=RequestMethod.POST)
	public AlvesJSONResult queryResources(@RequestBody DataType dataType) {
		List<DataType> list = dataTypeService.queryResources(dataType);
		for (DataType type : list) {
			if (type.getIcon() != null) {
				type.setCommonIcon(BaseObject.host + "/img/" + type.getIcon() + "-common.png");
				type.setActiveIcon(BaseObject.host + "/img/" + type.getIcon() + "-active.png");
			}
		}
		return AlvesJSONResult.ok(list);
	}

	@RequestMapping(name="获取战时一张图资源列表",value="/getWarMapResourceList",method=RequestMethod.GET)
	public List<DataType> getWarMapResourceList() {
		return dataTypeService.getWarMapResourceList();
	}

	@Api2Doc(order = 6)
    @ApiComment(value="插入应急资源类型")
	@RequestMapping(name="插入应急资源类型",value="/insertEType",method=RequestMethod.POST)
	public AlvesJSONResult insertEType(@Validated @ApiComment(value="修改应急资源类型",sample="根据id查询应急资源类型接口可查看字段信息") @RequestBody EType pojo) {
		int result = eTypeService.insert(pojo);
		if (result > 0) {
			return AlvesJSONResult.ok();
		} else {
			throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "应急资源类型保存失败");
		}
	}

	@Api2Doc(order = 7)
    @ApiComment(value="修改应急资源类型")
    @RequestMapping(name="修改应急资源类型",value="/updateEType",method=RequestMethod.POST)
    public AlvesJSONResult update(@ApiComment(value="修改应急资源类型",sample="根据id查询应急资源类型接口可查看字段信息") @RequestBody EType pojo) {
		boolean result = eTypeService.update(pojo);
		if (result) {
			return AlvesJSONResult.ok();
		} else {
			throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "应急资源类型保存失败");
		}
    }

	@Api2Doc(order = 8)
    @ApiComment(value="删除应急资源类型")
    @RequestMapping(name="删除应急资源类型",value="/deleteEType",method=RequestMethod.GET)
    public AlvesJSONResult delete(@ApiComment(value="应急资源类型id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "ids为空");
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
    		EType pojo = eTypeService.selectById(Integer.valueOf(id));
    		eTypeService.delete(pojo);
    	}
        return AlvesJSONResult.ok();
    }

	@Api2Doc(order = 9)
    @ApiComment(value="根据id查询应急资源类型")
    @RequestMapping(name="根据id查询应急资源类型",value="/selectETypeById",method=RequestMethod.GET)
    public AlvesJSONResult selectETypeById(@ApiComment(value="应急资源类型id",sample="1") Integer id) {
		EType pojo = eTypeService.selectById(id);
        return AlvesJSONResult.ok(pojo);
    }

	@Api2Doc(order = 10)
    @ApiComment(value="列出应急资源类型")
    @RequestMapping(name="列出应急资源类型",value="/listEType",method=RequestMethod.POST)
    public AlvesJSONResult list(@ApiComment(value="应急资源类型",sample="根据id查询应急资源类型接口可查看字段信息") @RequestBody EType pojo) {
    	ETypeResult result = new ETypeResult();
        List<EType> list = eTypeService.queryForPage(pojo, pojo.getPage(), pojo.getPageSize());
        Long count = eTypeService.queryForCounts(pojo);
        result.setList(list);
        result.setCount(count);
        return AlvesJSONResult.ok(result);
    }

}
