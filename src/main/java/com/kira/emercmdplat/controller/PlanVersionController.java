package com.kira.emercmdplat.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.DataType;
import com.kira.emercmdplat.pojo.Duty;
import com.kira.emercmdplat.pojo.DutyExtent;
import com.kira.emercmdplat.pojo.PlanCatalog;
import com.kira.emercmdplat.pojo.PlanGroup;
import com.kira.emercmdplat.pojo.PlanOrg;
import com.kira.emercmdplat.pojo.PlanParam;
import com.kira.emercmdplat.pojo.PlanResponse;
import com.kira.emercmdplat.pojo.PlanResponseFlow;
import com.kira.emercmdplat.pojo.PlanResponseFlowTask;
import com.kira.emercmdplat.pojo.PlanResponseGuard;
import com.kira.emercmdplat.pojo.PlanTag;
import com.kira.emercmdplat.pojo.PlanVersion;
import com.kira.emercmdplat.pojo.PlanVersionResult;
import com.kira.emercmdplat.service.DataTypeService;
import com.kira.emercmdplat.service.DutyService;
import com.kira.emercmdplat.service.PlanTypeService;
import com.kira.emercmdplat.service.PlanVersionService;
import com.kira.emercmdplat.utils.Node;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;

@Api2Doc(id = "planVersion", name = "预案管理接口", order = 2)
@RestController
@RequestMapping("/planVersion")
public class PlanVersionController extends BaseController {
	
	@Autowired
	private PlanVersionService planVersionService;
	
	@Autowired
	private PlanTypeService planTypeService;
	
	@Autowired
	private DutyService dutyService;
	
	@Autowired
	private DataTypeService dataTypeService;
	
	@Api2Doc(order = 1)
    @ApiComment(value="列出预案列表")
	@RequestMapping(name="列出预案列表",value="/listVersions",method=RequestMethod.POST)
	public PlanVersionResult listVersions(@ApiComment(value="查询参数",sample="{name:'aaa'}") PlanVersion planVersion,@ApiComment("第几页") Integer page,@ApiComment("每页显示条数") Integer pageSize) {
		PlanVersionResult result = new PlanVersionResult();
		List<PlanVersion> list = planVersionService.listVersions(planVersion, page, pageSize);
		for (PlanVersion pv : list) {
			Integer type = pv.getType();
			pv.setTypeName(planTypeService.getPlanTypeById(type).getName());
			Integer userId = pv.getUserId();
			pv.setUserName(dutyService.selectById(userId).getName());
			String params = pv.getParams();
			String tags = pv.getTags();
			if (params != null) {
				List<PlanParam> paramList = planTypeService.queryForParamIds(Arrays.asList(params.split(",")));
				pv.setParamList(paramList);
			}
			if (tags != null) {
				List<PlanTag> tagList = planTypeService.queryForTagIds(Arrays.asList(tags.split(",")));
				pv.setTagList(tagList);
			}
		}
		Long count = planVersionService.countVersions(planVersion);
		result.setList(list);
    	result.setCount(count);
		return result;
	}
	
	@Api2Doc(order = 31)
    @ApiComment(value="根据id取得预案")
	@RequestMapping(name="根据id取得预案",value="/getVersionById",method=RequestMethod.GET)
	public PlanVersion getVersionById(@ApiComment("预案id") Integer id) {
		PlanVersion pv = planVersionService.getPlanVerionById(id);
		Integer type = pv.getType();
		pv.setTypeName(planTypeService.getPlanTypeById(type).getName());
		Integer userId = pv.getUserId();
		pv.setUserName(dutyService.selectById(userId).getName());
		String params = pv.getParams();
		String tags = pv.getTags();
		if (params != null) {
			List<PlanParam> paramList = planTypeService.queryForParamIds(Arrays.asList(params.split(",")));
			pv.setParamList(paramList);
		}
		if (tags != null) {
			List<PlanTag> tagList = planTypeService.queryForTagIds(Arrays.asList(tags.split(",")));
			pv.setTagList(tagList);
		}
		return pv;
	}
	
	@Api2Doc(order = 2)
	@ApiComment("插入预案，参数类型参见列出预案列表")
	@RequestMapping(name="插入预案",value="/insertVersion",method=RequestMethod.POST)
	public int insertVersion(@ApiComment(value="插入预案",sample="{id:1,name:'aaa',version:'1',type:1,code:'1',org:'aaa',userId:1,pubTime:'2020-04-14',scope:'aaa',params:'1,2,3',tags:'1,2,3'}") PlanVersion planVersion) {
		int id = planVersionService.insertVersion(planVersion);
		return id;
	}
	
	@Api2Doc(order = 3)
	@ApiComment("修改预案，参数类型参见列出预案列表")
	@RequestMapping(name="修改预案",value="/updateVersion",method=RequestMethod.POST)
	public String updateVersion(@ApiComment(value="修改预案",sample="{id:1,name:'aaa',version:'1',type:1,code:'1',org:'aaa',userId:1,pubTime:'2020-04-14',scope:'aaa',params:'1,2,3',tags:'1,2,3'}") PlanVersion planVersion) {
		planVersionService.updateVersion(planVersion);
		return "success";
	}
	
	@Api2Doc(order = 4)
	@ApiComment(value="删除预案")
	@RequestMapping(name="删除预案",value="/deleteVersion",method=RequestMethod.GET)
	public String deleteVersion(@ApiComment("预案id") Integer id) {
		planVersionService.deleteVersion(id);
		return "success";
	}
	
	@Api2Doc(order = 5)
    @ApiComment(value="列出预案组织树")
	@RequestMapping(name="列出预案组织树",value="/listOrgTree",method=RequestMethod.POST)
	public List<Node> listOrgTree(@ApiComment(value="查询参数",sample="{name:'aaa',pvId:1}") PlanOrg planOrg) {
		List<Node> list = planVersionService.listOrgTree(planOrg);
		return list;
	}
	
	@Api2Doc(order = 26)
    @ApiComment(value="根据id取得预案组织")
	@RequestMapping(name="根据id取得预案组织",value="/getOrgById",method=RequestMethod.GET)
	public PlanOrg getOrgById(@ApiComment("预案组织id") Integer id) {
		PlanOrg planOrg = planVersionService.getOrgById(id);
		String userIds = planOrg.getUserIds();
		List<DutyExtent> userList = dutyService.queryForIds(Arrays.asList(userIds.split(",")));
		planOrg.setUserList(userList);
		return planOrg;
	}
	
	@Api2Doc(order = 6)
    @ApiComment(value="插入预案组织树")
	@RequestMapping(name="插入预案组织树",value="/insertOrg",method=RequestMethod.POST)
	public PlanOrg insertOrg(PlanOrg planOrg) {
		planVersionService.insertOrg(planOrg);
		return planOrg;
	}
	
	@Api2Doc(order = 7)
    @ApiComment(value="修改预案组织树")
	@RequestMapping(name="修改预案组织树",value="/updateOrg",method=RequestMethod.POST)
	public PlanOrg updateOrg(PlanOrg planOrg) {
		planVersionService.updateOrg(planOrg);
		return planOrg;
	}
	
	@Api2Doc(order = 8)
    @ApiComment(value="删除预案组织树")
	@RequestMapping(name="删除预案组织树",value="/deleteOrg",method=RequestMethod.GET)
	public String deleteOrg(@ApiComment("预案组织id") Integer id) {
		planVersionService.deleteOrg(id);
		return "success";
	}
	
	@Api2Doc(order = 9)
    @ApiComment(value="列出预案响应")
	@RequestMapping(name="列出预案响应",value="/listResponses",method=RequestMethod.GET)
	public List<PlanResponse> listResponses(@ApiComment("预案id") int pvId) {
		List<PlanResponse> list = planVersionService.listResponses(pvId);
		return list;
	}
	
	@Api2Doc(order = 10)
    @ApiComment(value="插入预案响应")
	@RequestMapping(name="插入预案响应",value="/insertResponse",method=RequestMethod.POST)
	public String insertResponse(@ApiComment(value="插入预案响应",sample="{id:1,level:'一级响应',color:1,type:1,desc:'aaa',pvId:1}") PlanResponse planResponse) {
		planVersionService.insertResponse(planResponse);
		return "success";
	}
	
	@Api2Doc(order = 11)
    @ApiComment(value="删除预案响应")
	@RequestMapping(name="删除预案响应",value="/deleteResponse",method=RequestMethod.GET)
	public String deleteResponse(@ApiComment("预案响应id") Integer id) {
		planVersionService.deleteResponse(id);
		return "success";
	}
	
	@Api2Doc(order = 12)
    @ApiComment(value="列出预案响应流程")
	@RequestMapping(name="列出预案响应流程",value="/listResponseFlows",method=RequestMethod.GET)
	public List<PlanResponseFlow> listResponseFlows(@ApiComment("预案响应id") int prId) {
		List<PlanResponseFlow> list = planVersionService.listResponseFlows(prId);
		for (PlanResponseFlow prf : list) {
			String groupIds = prf.getGroupIds();
			List<PlanGroup> groupList = planTypeService.queryForGroupIds(Arrays.asList(groupIds.split(",")));
			prf.setGroupList(groupList);
		}
		return list;
	}
	
	@Api2Doc(order = 13)
    @ApiComment(value="插入预案响应流程")
	@RequestMapping(name="插入预案响应流程",value="/insertResponseFlow",method=RequestMethod.POST)
	public String insertResponseFlow(@ApiComment(value="插入预案响应流程",sample="{id:1,name:'aaa',content:'aaa',groupIds:'1,2,3',prId:1}") PlanResponseFlow planResponseFlow) {
		planVersionService.insertResponseFlow(planResponseFlow);
		return "success";
	}
	
	@Api2Doc(order = 14)
    @ApiComment(value="删除预案响应流程")
	@RequestMapping(name="删除预案响应流程",value="/deleteResponseFlow",method=RequestMethod.GET)
	public String deleteResponseFlow(@ApiComment("预案响应流程id") Integer id) {
		planVersionService.deleteResponseFlow(id);
		return "success";
	}
	
	@Api2Doc(order = 15)
    @ApiComment(value="列出预案响应流程任务")
	@RequestMapping(name="列出预案响应流程任务",value="/listResponseFlowTasks",method=RequestMethod.GET)
	public List<PlanResponseFlowTask> listResponseFlowTasks(@ApiComment("预案响应流程id") int prfId) {
		List<PlanResponseFlowTask> list = planVersionService.listResponseFlowTasks(prfId);
		for (PlanResponseFlowTask prft : list) {
			PlanGroup group = planTypeService.getGroupById(prft.getGroupId());
			prft.setGroup(group);
		}
		return list;
	}
	
	@Api2Doc(order = 16)
    @ApiComment(value="插入预案响应流程任务")
	@RequestMapping(name="插入预案响应流程任务",value="/insertResponseFlowTask",method=RequestMethod.POST)
	public String insertResponseFlowTask(@ApiComment(value="插入预案响应流程",sample="{id:1,name:'aaa',desc:'aaa',groupId:'1',prfId:1}") PlanResponseFlowTask planResponseFlowTask) {
		planVersionService.insertResponseFlowTask(planResponseFlowTask);
		return "success";
	}
	
	@Api2Doc(order = 17)
    @ApiComment(value="删除预案响应流程任务")
	@RequestMapping(name="删除预案响应流程任务",value="/deleteResponseFlowTask",method=RequestMethod.GET)
	public String deleteResponseFlowTask(@ApiComment("预案响应流程任务id") Integer id) {
		planVersionService.deleteResponseFlowTask(id);
		return "success";
	}
	
	@Api2Doc(order = 18)
    @ApiComment(value="列出预案响应保障树")
	@RequestMapping(name="列出预案响应保障树",value="/listResponseGuardTree",method=RequestMethod.POST)
	public List<Node> listResponseGuardTree(@ApiComment(value="查询参数",sample="{name:'aaa',prId:1}") PlanResponseGuard planResponseGuard) {
		List<Node> list = planVersionService.listResponseGuardTree(planResponseGuard);
		return list;
	}
	
	@Api2Doc(order = 27)
    @ApiComment(value="根据id取得预案响应保障")
	@RequestMapping(name="根据id取得预案响应保障",value="/getResponseGuardById",method=RequestMethod.GET)
	public PlanResponseGuard getResponseGuardById(@ApiComment("预案响应保障id") Integer id) {
		PlanResponseGuard planResponseGuard = planVersionService.getResponseGuardById(id);
		String json  = planResponseGuard.getRes();
		System.out.println(json);
		if (!StringUtils.isEmpty(json)) {
			List<DataType> resList = dataTypeService.getResourcesByJson(json);
			if (resList.size() > 0) {
				DataType d = resList.get(0);
				planResponseGuard.setResName(d.getTypeName() + "-" + d.getName() + "等");
			}
			planResponseGuard.setResList(resList);
		}
		return planResponseGuard;
	}
	
	@Api2Doc(order = 29)
    @ApiComment(value="获取保障资源类型")
	@RequestMapping(name="获取保障资源类型",value="/getResourceTypes",method=RequestMethod.GET)
	public List<DataType> getResourceTypes() {
		DataType d = new DataType();
		d.setType(1);
		return dataTypeService.queryForAll(d);
	}
	
	@Api2Doc(order = 30)
    @ApiComment(value="获取保障资源项列表")
	@RequestMapping(name="获取保障资源项列表",value="/getResourcesByType",method=RequestMethod.GET)
	public List<DataType> getResourcesByType(@ApiComment("保障资源类型id") int id) {
		return dataTypeService.getResourcesByType(id);
	}
	
	@Api2Doc(order = 19)
    @ApiComment(value="插入预案响应保障")
	@RequestMapping(name="插入预案响应保障",value="/insertResponseGuard",method=RequestMethod.POST)
	public PlanResponseGuard insertResponseGuard(PlanResponseGuard planResponseGuard) {
		planVersionService.insertResponseGuard(planResponseGuard);
		return planResponseGuard;
	}
	
	@Api2Doc(order = 20)
    @ApiComment(value="修改预案响应保障")
	@RequestMapping(name="修改预案响应保障",value="/updateResponseGuard",method=RequestMethod.POST)
	public PlanResponseGuard updateResponseGuard(PlanResponseGuard planResponseGuard) {
		planVersionService.updateResponseGuard(planResponseGuard);
		return planResponseGuard;
	}
	
	@Api2Doc(order = 21)
    @ApiComment(value="删除预案响应保障")
	@RequestMapping(name="删除预案响应保障",value="/deleteResponseGuard",method=RequestMethod.GET)
	public String deleteResponseGuard(@ApiComment("预案响应保障id") Integer id) {
		planVersionService.deleteResponseGuard(id);
		return "success";
	}
	
	@Api2Doc(order = 22)
    @ApiComment(value="列出预案文本目录")
	@RequestMapping(name="列出预案文本目录",value="/listCatalogTree",method=RequestMethod.POST)
	public List<Node> listCatalogTree(@ApiComment(value="查询参数",sample="{node:'aaa',pvId:1}") PlanCatalog planCatalog) {
		List<Node> list = planVersionService.listCatalogTree(planCatalog);
		return list;
	}
	
	@Api2Doc(order = 28)
    @ApiComment(value="根据id取得预案文本目录")
	@RequestMapping(name="根据id取得预案文本目录",value="/getCatalogById",method=RequestMethod.GET)
	public PlanCatalog getCatalogById(@ApiComment("预案文本目录id") Integer id) {
		PlanCatalog planCatalog = planVersionService.getCatalogById(id);
		return planCatalog;
	}
	
	@Api2Doc(order = 23)
    @ApiComment(value="插入预案文本目录")
	@RequestMapping(name="插入预案文本目录",value="/insertCatalog",method=RequestMethod.POST)
	public PlanCatalog insertCatalog(PlanCatalog planCatalog) {
		planVersionService.insertCatalog(planCatalog);
		return planCatalog;
	}
	
	@Api2Doc(order = 24)
    @ApiComment(value="修改预案文本目录")
	@RequestMapping(name="修改预案文本目录",value="/updateCatalog",method=RequestMethod.POST)
	public PlanCatalog updateCatalog(PlanCatalog planCatalog) {
		planVersionService.updateCatalog(planCatalog);
		return planCatalog;
	}
	
	@Api2Doc(order = 25)
    @ApiComment(value="删除预案文本目录")
	@RequestMapping(name="删除预案文本目录",value="/deleteCatalog",method=RequestMethod.GET)
	public String deleteCatalog(@ApiComment("预案文本目录id")Integer id) {
		planVersionService.deleteCatalog(id);
		return "success";
	}

}
