package com.kira.emercmdplat.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.pojo.DataType;
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
import com.kira.emercmdplat.pojo.PlanVersionApproval;
import com.kira.emercmdplat.pojo.PlanVersionResult;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.DataTypeService;
import com.kira.emercmdplat.service.PlanTypeService;
import com.kira.emercmdplat.service.PlanVersionService;
import com.kira.emercmdplat.utils.DateUtil;
import com.kira.emercmdplat.utils.Node;
import com.kira.emercmdplat.utils.PropertiesUtils;
import com.kira.emercmdplat.utils.TokenUtil;
import com.kira.emercmdplat.utils.file.FileResult;
import com.kira.emercmdplat.utils.file.FileuploadUtil;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;

import net.sf.json.JSONObject;

@Api2Doc(id = "planVersion", name = "预案管理接口", order = 2)
@RestController
@RequestMapping("/planVersion")
public class PlanVersionController extends BaseController {

	@Autowired
	private PlanVersionService planVersionService;

	@Autowired
	private PlanTypeService planTypeService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private DataTypeService dataTypeService;

	@Api2Doc(order = 1)
    @ApiComment(value="列出预案列表")
	@RequestMapping(name="列出预案列表",value="/listVersions",method=RequestMethod.POST)
	public PlanVersionResult listVersions(@ApiComment(value="查询参数",sample="{name:'aaa'}") @RequestBody PlanVersion planVersion) {
		PlanVersionResult result = new PlanVersionResult();
		List<PlanVersion> list = planVersionService.listVersions(planVersion);
		for (PlanVersion pv : list) {
			Integer type = pv.getType();
			pv.setTypeName(planTypeService.getPlanTypeById(type).getName());
			Integer userId = pv.getUserId();
			ContactsResult user = contactService.selectById(new Long(userId));
			if (user != null) {
				pv.setUserName(user.getContactName());
			}
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

	@Api2Doc(order = 34)
    @ApiComment(value="上传预案文件")
	@RequestMapping(name="上传预案文件",value="/fileUpload",method=RequestMethod.POST)
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            String path = PropertiesUtils.getInstance().getProperty("attachmentPath");
            String extension = PropertiesUtils.getInstance().getProperty("pdfExtension");
            FileResult fileResult = FileuploadUtil.saveFile(file, path, extension);
            return JSONObject.fromObject(fileResult).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

	@Api2Doc(order = 31)
    @ApiComment(value="根据id取得预案")
	@RequestMapping(name="根据id取得预案",value="/getVersionById",method=RequestMethod.GET)
	public PlanVersion getVersionById(@ApiComment("预案id") Integer id) {
		PlanVersion pv = planVersionService.getPlanVerionById(id);
		Integer type = pv.getType();
		pv.setTypeName(planTypeService.getPlanTypeById(type).getName());
		Integer userId = pv.getUserId();
		ContactsResult user = contactService.selectById(new Long(userId));
		if (user != null) {
			pv.setUserName(user.getContactName());
		}
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
	public int insertVersion(@ApiComment(value="插入预案",sample="{id:1,name:'aaa',version:'1',type:1,code:'1',org:'aaa',userId:1,pubTime:'2020-04-14',scope:'aaa',params:'1,2,3',tags:'1,2,3'}") @RequestBody PlanVersion planVersion,HttpServletRequest request) {
		String nowStr = DateUtil.getNowStr("yyyy-MM-dd hh:mm:ss");
		planVersion.setCreateTime(nowStr);
		planVersion.setStatus(0);//编制中
		planVersion.setUserId(getLoginUser(request));
		int id = planVersionService.insertVersion(planVersion);
		return id;
	}

	@Api2Doc(order = 3)
	@ApiComment("修改预案，参数类型参见列出预案列表")
	@RequestMapping(name="修改预案",value="/updateVersion",method=RequestMethod.POST)
	public String updateVersion(@ApiComment(value="修改预案",sample="{id:1,name:'aaa',version:'1',type:1,code:'1',org:'aaa',userId:1,pubTime:'2020-04-14',scope:'aaa',params:'1,2,3',tags:'1,2,3'}") @RequestBody PlanVersion planVersion) {
		if (planVersion.getStatus() != null && planVersion.getStatus() == 4) {//已发布
			String nowStr = DateUtil.getNowStr("yyyy-MM-dd hh:mm:ss");
			planVersion.setPubTime(nowStr);
		}
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
	public List<Node> listOrgTree(@ApiComment(value="查询参数",sample="{name:'aaa',pvId:1}") @RequestBody PlanOrg planOrg) {
		List<Node> list = planVersionService.listOrgTree(planOrg);
		return list;
	}

	@Api2Doc(order = 26)
    @ApiComment(value="根据id取得预案组织")
	@RequestMapping(name="根据id取得预案组织",value="/getOrgById",method=RequestMethod.GET)
	public PlanOrg getOrgById(@ApiComment("预案组织id") Integer id) {
		PlanOrg planOrg = planVersionService.getOrgById(id);
		String userIds = planOrg.getUserIds();
		List<ContactsResult> userList = contactService.queryForIds(Arrays.asList(userIds.split(",")));
		planOrg.setUserList(userList);
		return planOrg;
	}

	@Api2Doc(order = 6)
    @ApiComment(value="插入预案组织树")
	@RequestMapping(name="插入预案组织树",value="/insertOrg",method=RequestMethod.POST)
	public PlanOrg insertOrg(@RequestBody PlanOrg planOrg) {
		planVersionService.insertOrg(planOrg);
		return planOrg;
	}

	@Api2Doc(order = 7)
    @ApiComment(value="修改预案组织树")
	@RequestMapping(name="修改预案组织树",value="/updateOrg",method=RequestMethod.POST)
	public PlanOrg updateOrg(@RequestBody PlanOrg planOrg) {
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
	public List<PlanResponse> listResponses(@ApiComment("预案id") int pvId,@ApiComment("预警/应急响应") int form) {
		List<PlanResponse> list = planVersionService.listResponses(pvId,form);
		for (PlanResponse pr : list) {
			String params = pr.getParams();
			if (!StringUtils.isEmpty(params)) {
				List<PlanParam> paramList = planTypeService.getParamByJson(params);
				pr.setParamList(paramList);
			}
		}
		return list;
	}

	@Api2Doc(order = 32)
    @ApiComment(value="根据id取得预案响应")
	@RequestMapping(name="根据id取得预案响应",value="/getResponseById",method=RequestMethod.GET)
	public PlanResponse getResponseById(@ApiComment("预案响应id") Integer id) {
		PlanResponse planResponse = planVersionService.getResponseById(id);
		String params = planResponse.getParams();
		if (!StringUtils.isEmpty(params)) {
			List<PlanParam> paramList = planTypeService.getParamByJson(params);
			planResponse.setParamList(paramList);
		}
		return planResponse;
	}

	@Api2Doc(order = 10)
    @ApiComment(value="插入预案响应")
	@RequestMapping(name="插入预案响应",value="/insertResponse",method=RequestMethod.POST)
	public int insertResponse(@ApiComment(value="插入预案响应",sample="{id:1,form:0,level:'一级响应',color:1,type:1,desc:'aaa',params:'[{id:1,value:\'aaa\'}]',pvId:1}") @RequestBody PlanResponse planResponse) {
		int id = planVersionService.insertResponse(planResponse);
		return id;
	}

	@Api2Doc(order = 33)
    @ApiComment(value="修改预案响应")
	@RequestMapping(name="修改预案响应",value="/updateResponse",method=RequestMethod.POST)
	public String updateResponse(@ApiComment(value="修改预案响应",sample="{id:1,form:0,level:'一级响应',color:1,type:1,desc:'aaa',params:'[{id:1,value:\'aaa\'}]',pvId:1}") @RequestBody PlanResponse planResponse) {
		planVersionService.updateReponse(planResponse);
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
			if (groupIds != null) {
				List<PlanGroup> groupList = planTypeService.queryForGroupIds(Arrays.asList(groupIds.split(",")));
				prf.setGroupList(groupList);
			}
		}
		return list;
	}

	@Api2Doc(order = 13)
    @ApiComment(value="插入预案响应流程")
	@RequestMapping(name="插入预案响应流程",value="/insertResponseFlow",method=RequestMethod.POST)
	public String insertResponseFlow(@ApiComment(value="插入预案响应流程",sample="{id:1,name:'aaa',content:'aaa',groupIds:'1,2,3',prId:1}") @RequestBody PlanResponseFlow planResponseFlow) {
		planVersionService.insertResponseFlow(planResponseFlow);
		return "success";
	}

	@Api2Doc(order = 39)
    @ApiComment(value="修改预案响应流程")
	@RequestMapping(name="修改预案响应流程",value="/updateResponseFlow",method=RequestMethod.POST)
	public String updateResponseFlow(@ApiComment(value="修改预案响应流程",sample="{id:1,name:'aaa',content:'aaa',groupIds:'1,2,3',prId:1}") @RequestBody PlanResponseFlow planResponseFlow) {
		planVersionService.updateResponseFlow(planResponseFlow);
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
			String json = prft.getGroupId();
			System.out.println(json);
			if (!StringUtils.isEmpty(json)) {
				List<DataType> groupList = dataTypeService.getFlowTaskMembers(json);
				prft.setGroupList(groupList);
			}
		}
		return list;
	}

	@Api2Doc(order = 16)
    @ApiComment(value="插入预案响应流程任务")
	@RequestMapping(name="插入预案响应流程任务",value="/insertResponseFlowTask",method=RequestMethod.POST)
	public String insertResponseFlowTask(@ApiComment(value="插入预案响应流程任务",sample="{id:1,name:'aaa',desc:'aaa',groupId:'1',prfId:1}") @RequestBody PlanResponseFlowTask planResponseFlowTask) {
		planVersionService.insertResponseFlowTask(planResponseFlowTask);
		return "success";
	}

	@Api2Doc(order = 40)
    @ApiComment(value="修改预案响应流程任务")
	@RequestMapping(name="修改预案响应流程任务",value="/updateResponseFlowTask",method=RequestMethod.POST)
	public String updateResponseFlowTask(@ApiComment(value="修改预案响应流程任务",sample="{id:1,name:'aaa',desc:'aaa',groupId:'1',prfId:1}") @RequestBody PlanResponseFlowTask planResponseFlowTask) {
		planVersionService.updateResponseFlowTask(planResponseFlowTask);
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
	public List<Node> listResponseGuardTree(@ApiComment(value="查询参数",sample="{name:'aaa',prId:1}") @RequestBody PlanResponseGuard planResponseGuard) {
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
	public PlanResponseGuard insertResponseGuard(@RequestBody PlanResponseGuard planResponseGuard) {
		planVersionService.insertResponseGuard(planResponseGuard);
		return planResponseGuard;
	}

	@Api2Doc(order = 20)
    @ApiComment(value="修改预案响应保障")
	@RequestMapping(name="修改预案响应保障",value="/updateResponseGuard",method=RequestMethod.POST)
	public PlanResponseGuard updateResponseGuard(@RequestBody PlanResponseGuard planResponseGuard) {
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
	public List<Node> listCatalogTree(@ApiComment(value="查询参数",sample="{node:'aaa',pvId:1}") @RequestBody PlanCatalog planCatalog) {
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
	public PlanCatalog insertCatalog(@RequestBody PlanCatalog planCatalog) {
		planVersionService.insertCatalog(planCatalog);
		return planCatalog;
	}

	@Api2Doc(order = 24)
    @ApiComment(value="修改预案文本目录")
	@RequestMapping(name="修改预案文本目录",value="/updateCatalog",method=RequestMethod.POST)
	public PlanCatalog updateCatalog(@RequestBody PlanCatalog planCatalog) {
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

	@Api2Doc(order = 35)
    @ApiComment(value="提交预案审核")
	@RequestMapping(name="提交预案审核",value="/insertPlanVersionApproval",method=RequestMethod.POST)
	public String insertPlanVersionApproval(@ApiComment(value="提交预案审核",sample="参看列出预案审核接口查看预案审核对象") @RequestBody PlanVersionApproval planVersionApproval, HttpServletRequest request) {
		int userId = getLoginUser(request);
		planVersionApproval.setSubmitter(userId);
		planVersionApproval.setCreateTime(new Date());
		planVersionService.insertPlanVersionApproval(planVersionApproval);
		PlanVersion planVersion = new PlanVersion();
		planVersion.setId(planVersionApproval.getPvId());
		planVersion.setStatus(1);//审核中
		planVersionService.updateVersion(planVersion);
		return "success";
	}

	private int getLoginUser(HttpServletRequest request) {
		String token = TokenUtil.getRequestToken(request);
        if (StringUtils.isBlank(token)) {
            return -1;
        }
        ContactsResult userEntity = contactService.findByToken(token);
        if (userEntity == null) {
            return -1;
        }
		return userEntity.getId().intValue();
	}

	@Api2Doc(order = 36)
    @ApiComment(value="预案审核")
	@RequestMapping(name="预案审核",value="/updatePlanVersionApproval",method=RequestMethod.POST)
	public String updatePlanVersionApproval(@ApiComment(value="提交预案审核",sample="参看列出预案审核接口查看预案审核对象") @RequestBody PlanVersionApproval planVersionApproval, HttpServletRequest request) {
		int userId = getLoginUser(request);
		planVersionApproval.setExaminer(userId);
		planVersionApproval.setExamineTime(new Date());
		planVersionService.updatePlanVersionApproval(planVersionApproval);
		PlanVersion planVersion = planVersionService.getPlanVerionById(planVersionApproval.getPvId());
		if (planVersionApproval.getStatus() == 1) {//审核通过
			planVersion.setStatus(2);
			String version = planVersion.getVersion();
			version = String.valueOf(Double.parseDouble(version) +  1);
			planVersion.setVersion(version);
		} else {//审核不通过
			planVersion.setStatus(3);
		}
		planVersionService.updateVersion(planVersion);
		return "success";
	}

	@Api2Doc(order = 37)
    @ApiComment(value="列出预案审核")
	@RequestMapping(name="列出预案审核",value="/listPlanVersionApproval",method=RequestMethod.GET)
	public List<PlanVersionApproval> listPlanVersionApproval(@ApiComment("预案id") Integer pvId) {
		List<PlanVersionApproval> list = planVersionService.listPlanVersionApprovals(pvId);
		return list;
	}

	@Api2Doc(order = 38)
    @ApiComment(value="复制组织")
	@RequestMapping(name="复制组织",value="/copyOrg",method=RequestMethod.GET)
	public String copyOrg(@ApiComment("预案id") Integer pvId) {
		planVersionService.copyOrg(pvId);
		return "success";
	}
}
