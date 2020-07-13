package com.kira.emercmdplat.controller;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.pojo.EmergencyTeam;
import com.kira.emercmdplat.pojo.EmergencyTeamResult;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.EmergencyTeamService;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:03
 * @Description:
 */
@Api2Doc(id = "emergencyTeam", name = "应急队伍接口", order = 10)
@RestController
@RequestMapping("/emergencyTeam")
public class EmergencyTeamController extends BaseController {

    @Autowired
    private EmergencyTeamService emergencyTeamService;

    @Autowired
	private ContactService contactService;

    @Api2Doc(order = 1)
    @ApiComment(value="添加应急队伍")
    @RequestMapping(name="添加应急队伍",value="/add",method=RequestMethod.POST)
    public String insert(@Validated @ApiComment(value="添加应急队伍",sample="根据id查询应急队伍接口可查看字段信息") @RequestBody EmergencyTeam emergencyTeam) {
    	Contacts contact = new Contacts();
    	contact.setTelephone(emergencyTeam.getCellNum());
    	List<ContactsResult> result = contactService.queryForAll(contact);
    	if (result != null && result.size() == 1) {
    		ContactsResult contactsResult = result.get(0);
    		emergencyTeam.setPIC(contactsResult.getContactName());
    		emergencyTeam.setContactsId(contactsResult.getId());
    	}
        emergencyTeamService.insert(emergencyTeam);
        return "success";
    }

    @Api2Doc(order = 2)
    @ApiComment(value="修改应急队伍")
    @RequestMapping(name="修改应急队伍",value="/update",method=RequestMethod.POST)
    public String update(@Validated @ApiComment(value="修改应急队伍",sample="根据id查询应急队伍接口可查看字段信息") @RequestBody EmergencyTeam emergencyTeam) {
        emergencyTeamService.update(emergencyTeam);
        return "success";
    }

    @Api2Doc(order = 3)
    @ApiComment(value="删除应急队伍")
    @RequestMapping(name="删除应急队伍",value="/delete",method=RequestMethod.GET)
    public String delete(@ApiComment(value="应急队伍id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		return "fail";
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
    		EmergencyTeam emergencyTeam = emergencyTeamService.selectById(Integer.valueOf(id));
    		emergencyTeamService.delete(emergencyTeam);
    	}
        return "success";
    }

    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询应急队伍")
    @RequestMapping(name="根据id查询应急队伍",value="/selectById",method=RequestMethod.GET)
    public EmergencyTeam selectById(@ApiComment(value="应急队伍id",sample="1") Integer id) {
        EmergencyTeam emergencyTeam = emergencyTeamService.selectById(id);
        return emergencyTeam;
    }

    @Api2Doc(order = 5)
    @ApiComment(value="列出应急队伍")
    @RequestMapping(name="列出应急队伍",value="/list",method=RequestMethod.POST)
    public EmergencyTeamResult list(@ApiComment(value="应急队伍参数",sample="根据id查询应急队伍接口可查看字段信息") @RequestBody EmergencyTeam emergencyTeam) {
    	EmergencyTeamResult result = new EmergencyTeamResult();
        List<EmergencyTeam> list = emergencyTeamService.queryForPage(emergencyTeam, emergencyTeam.getPage(), emergencyTeam.getPageSize());
        Long count = emergencyTeamService.queryForCounts(emergencyTeam);
        result.setList(list);
        result.setCount(count);
        return result;
    }
}
