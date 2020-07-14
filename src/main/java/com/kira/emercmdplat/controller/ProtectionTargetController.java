package com.kira.emercmdplat.controller;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.ProtectionTargetService;
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
@Api2Doc(id = "protectionTarget", name = "防护目标接口", order = 12)
@RestController
@RequestMapping("/protectionTarget")
public class ProtectionTargetController extends BaseController {

    @Autowired
    private ProtectionTargetService protectionTargetService;

    @Autowired
	private ContactService contactService;

    @Api2Doc(order = 1)
    @ApiComment(value="添加防护目标")
    @RequestMapping(name="添加防护目标",value="/add",method=RequestMethod.POST)
    public String insert(@Validated @ApiComment(value="添加防护目标",sample="根据id查询防护目标接口可查看字段信息") @RequestBody ProtectionTarget protectionTarget) {
    	Contacts contact = new Contacts();
    	contact.setTelephone(protectionTarget.getCellNum());
    	List<ContactsResult> result = contactService.queryForAllOrPage(contact);
    	if (result != null && result.size() == 1) {
    		ContactsResult contactsResult = result.get(0);
    		protectionTarget.setPIC(contactsResult.getContactName());
    		protectionTarget.setContactsId(contactsResult.getId());
    	}
        protectionTargetService.insert(protectionTarget);
        return "success";
    }

    @Api2Doc(order = 2)
    @ApiComment(value="修改防护目标")
    @RequestMapping(name="修改防护目标",value="/update",method=RequestMethod.POST)
    public String update(@Validated @ApiComment(value="修改防护目标",sample="根据id查询防护目标接口可查看字段信息") @RequestBody ProtectionTarget protectionTarget) {
        protectionTargetService.update(protectionTarget);
        return "success";
    }

    @Api2Doc(order = 3)
    @ApiComment(value="删除防护目标")
    @RequestMapping(name="删除防护目标",value="/delete",method=RequestMethod.GET)
    public String delete(@ApiComment(value="防护目标id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		return "fail";
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
    		ProtectionTarget protectionTarget = protectionTargetService.selectById(Integer.valueOf(id));
    		protectionTargetService.delete(protectionTarget);
    	}
        return "success";
    }

    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询防护目标")
    @RequestMapping(name="根据id查询防护目标",value="/selectById",method=RequestMethod.GET)
    public ProtectionTarget selectById(@ApiComment(value="防护目标id",sample="1") Integer id) {
        ProtectionTarget protectionTarget = protectionTargetService.selectById(id);
        return protectionTarget;
    }

    @Api2Doc(order = 5)
    @ApiComment(value="列出防护目标")
    @RequestMapping(name="列出防护目标",value="/list",method=RequestMethod.POST)
    public ProtectionTargetResult list(@ApiComment(value="防护目标参数",sample="根据id查询防护目标接口可查看字段信息") @RequestBody ProtectionTarget protectionTarget) {
    	ProtectionTargetResult result = new ProtectionTargetResult();
        List<ProtectionTarget> list = protectionTargetService.queryForPage(protectionTarget, protectionTarget.getPage(), protectionTarget.getPageSize());
        for (ProtectionTarget pt : list) {
        	if (pt.getIcon() != null) {
        		pt.setCommonIcon(BaseObject.host + "/img/" + pt.getIcon() + "-common.png");
        		pt.setActiveIcon(BaseObject.host + "/img/" + pt.getIcon() + "-active.png");
        	}
        }
        Long count = protectionTargetService.queryForCounts(protectionTarget);
        result.setList(list);
        result.setCount(count);
        return result;
    }
}
