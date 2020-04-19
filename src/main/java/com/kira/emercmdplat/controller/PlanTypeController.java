package com.kira.emercmdplat.controller;

import java.util.Arrays;
import java.util.List;

import com.kira.emercmdplat.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.service.DutyService;
import com.kira.emercmdplat.service.PlanTypeService;
import com.kira.emercmdplat.utils.Node;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;

@Api2Doc(id = "planType", name = "预案配置接口", order = 1)
@RestController
@RequestMapping("/planType")
public class PlanTypeController extends BaseController {

	@Autowired
	private PlanTypeService planTypeService;

	@Autowired
	private DutyService dutyService;

	@Api2Doc(order = 1)
    @ApiComment(value="列出预案分类树")
	@RequestMapping(name="列出预案分类树",value="/listTypeTree",method=RequestMethod.POST)
	public List<Node> listTypeTree(@ApiComment("查询名字") String name) {
		List<Node> list = planTypeService.listTypeTree(name);
		return list;
	}

	@Api2Doc(order = 2)
	@ApiComment(value="插入预案类型，参数类型参见预案分类树")
	@RequestMapping(name="插入预案类型",value="/insertType",method=RequestMethod.POST)
	public String insertType(@ApiComment(value="插入预案类型",sample="{id:1,name:'aaa',order:1,parentId:0}") PlanType planType) {
		planTypeService.insertType(planType);
		return "success";
	}

	@Api2Doc(order = 3)
	@ApiComment(value="修改预案类型，参数类型参见预案分类树")
	@RequestMapping(name="修改预案类型",value="/updateType",method=RequestMethod.POST)
	public String updateType(@ApiComment(value="修改预案类型",sample="{id:1,name:'aaa',order:1,parentId:0}") PlanType planType) {
		planTypeService.updateType(planType);
		return "success";
	}

	@Api2Doc(order = 4)
	@ApiComment(value="删除预案类型")
	@RequestMapping(name="删除预案类型",value="/deleteType",method=RequestMethod.GET)
	public String deleteType(@ApiComment("预案类型id") Integer id) {
		planTypeService.deleteType(id);
		return "success";
	}

	@Api2Doc(order = 5)
	@ApiComment(value="列出预案标签")
	@RequestMapping(name="列出预案标签",value="/listTags",method=RequestMethod.GET)
	public List<PlanTag> listTags(@ApiComment("预案类型id") int ptId) {
		List<PlanTag> list = planTypeService.listTags(ptId);
		return list;
	}

	@Api2Doc(order = 6)
	@ApiComment(value="插入预案标签，参数类型参见列出预案标签")
	@RequestMapping(name="插入预案标签",value="/insertTag",method=RequestMethod.POST)
	public String insertTag(@ApiComment(value="插入预案标签",sample="{id:1,name:'aaa',ptId:3}") PlanTag planTag) {
		planTypeService.insertTag(planTag);
		return "success";
	}

	@Api2Doc(order = 7)
	@ApiComment(value="删除预案标签")
	@RequestMapping(name="删除预案标签",value="/deleteTag",method=RequestMethod.GET)
	public String deleteTag(@ApiComment("预案标签id") Integer id) {
		planTypeService.deleteTag(id);
		return "success";
	}

	@Api2Doc(order = 8)
	@ApiComment("列出预案参数")
	@RequestMapping(name="列出预案参数",value="/listParams",method=RequestMethod.GET)
	public PlanParamResult listParams(@ApiComment("预案类型id") int ptId,@ApiComment("第几页") Integer page,@ApiComment("每页显示条数") Integer pageSize) {
		PlanParamResult result = new PlanParamResult();
		List<PlanParam> list = planTypeService.listParams(ptId, page, pageSize);
		Long count = planTypeService.countParams(ptId);
		result.setList(list);
    	result.setCount(count);
		return result;
	}

	@Api2Doc(order = 9)
	@ApiComment("插入预案参数，参数类型参见列出预案参数")
	@RequestMapping(name="插入预案参数",value="/insertParam",method=RequestMethod.POST)
	public String insertParam(@ApiComment(value="插入预案参数",sample="{id:1,name:'aaa',type:1,unit:'人数',ptId:3}") PlanParam planParam) {
		planTypeService.insertParam(planParam);
		return "success";
	}

	@Api2Doc(order = 10)
	@ApiComment("修改预案参数，参数类型参见列出预案参数")
	@RequestMapping(name="修改预案参数",value="/updateParam",method=RequestMethod.POST)
	public String updateParam(@ApiComment(value="修改预案参数",sample="{id:1,name:'aaa',type:1,unit:'人数',ptId:3}") PlanParam planParam) {
		planTypeService.updateParam(planParam);
		return "success";
	}

	@Api2Doc(order = 11)
	@ApiComment(value="删除预案参数")
	@RequestMapping(name="删除预案参数",value="/deleteParam",method=RequestMethod.GET)
	public String deleteParam(@ApiComment("预案参数id") Integer id) {
		planTypeService.deleteParam(id);
		return "success";
	}

	@Api2Doc(order = 12)
	@ApiComment("列出预案组")
	@RequestMapping(name="列出预案组",value="/listGroups",method=RequestMethod.POST)
	public PlanGroupResult listGroups(@ApiComment(value="查询参数",sample="{name:'aaa',ptId:3}") PlanGroup planGroup,@ApiComment("第几页") Integer page,@ApiComment("每页显示条数") Integer pageSize) {
		PlanGroupResult result = new PlanGroupResult();
		List<PlanGroup> list = planTypeService.listGroups(planGroup, page, pageSize);
		for (PlanGroup pg : list) {
			String userIds = pg.getUserIds();
			List<Duty> userList = dutyService.queryForIds(Arrays.asList(userIds.split(",")));
			pg.setUserList(userList);
		}
		Long count = planTypeService.countGroups(planGroup);
		result.setCount(count);
		result.setList(list);
		return result;
	}

	@Api2Doc(order = 16)
	@ApiComment("列出组员列表")
	@RequestMapping(name="列出组员列表",value="/listUsers",method=RequestMethod.GET)
	public List<DutyExtent> listUsers() {
		return dutyService.queryForAll(null);
	}

	@Api2Doc(order = 13)
	@ApiComment("插入预案组，参数类型参见列出预案组")
	@RequestMapping(name="插入预案组",value="/insertGroup",method=RequestMethod.POST)
	public String insertGroup(@ApiComment(value="插入预案组",sample="{id:1,name:'aaa',leader:'aaa',userIds:'1,2,3',duty:'aaa',ptId:3}") PlanGroup planGroup) {
		planTypeService.insertGroup(planGroup);
		return "success";
	}

	@Api2Doc(order = 14)
	@ApiComment("修改预案组，参数类型参见列出预案组")
	@RequestMapping(name="修改预案组",value="/updateGroup",method=RequestMethod.POST)
	public String updateGroup(@ApiComment(value="修改预案组",sample="{id:1,name:'aaa',leader:'aaa',userIds:'1,2,3',duty:'aaa',ptId:3}") PlanGroup planGroup) {
		planTypeService.updateGroup(planGroup);
		return "success";
	}

	@Api2Doc(order = 15)
	@ApiComment(value="删除预案组")
	@RequestMapping(name="删除预案组",value="/deleteGroup",method=RequestMethod.GET)
	public String deleteGroup(@ApiComment("预案组id") Integer id) {
		planTypeService.deleteGroup(id);
		return "success";
	}

}
