package com.kira.emercmdplat.controller;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.HazardSourceService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
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
@Api2Doc(id = "hazardSource", name = "风险隐患接口", order = 8)
@RestController
@RequestMapping("/hazardSource")
public class HazardSourceController extends BaseController {

    @Autowired
    private HazardSourceService hazardSourceService;

    @Autowired
	private ContactService contactService;

    @Api2Doc(order = 1)
    @ApiComment(value="添加风险隐患")
    @RequestMapping(name="添加风险隐患",value="/add",method=RequestMethod.POST)
    public AlvesJSONResult insert(@Validated @ApiComment(value="添加风险隐患",sample="根据id查询风险隐患接口可查看字段信息") @RequestBody HazardSouce hazardSouce) {
    	Contacts contact = new Contacts();
    	contact.setTelephone(hazardSouce.getCellNum());
    	List<ContactsResult> result = contactService.queryForAllOrPage(contact);
    	if (result != null && result.size() == 1) {
    		ContactsResult contactsResult = result.get(0);
    		hazardSouce.setPIC(contactsResult.getContactName());
    		hazardSouce.setContactsId(contactsResult.getId());
    	}
        int count = hazardSourceService.insert(hazardSouce);
        if (count > 0) {
        	return AlvesJSONResult.ok();
        } else {
        	throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "风险隐患保存失败");
        }
    }

    @Api2Doc(order = 2)
    @ApiComment(value="修改风险隐患")
    @RequestMapping(name="修改风险隐患",value="/update",method=RequestMethod.POST)
    public AlvesJSONResult update(@Validated @ApiComment(value="修改风险隐患",sample="根据id查询风险隐患接口可查看字段信息") @RequestBody HazardSouce hazardSouce) {
        boolean result = hazardSourceService.update(hazardSouce);
        if (result) {
        	return AlvesJSONResult.ok();
        } else {
        	throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "风险隐患保存失败");
        }
    }

    @Api2Doc(order = 3)
    @ApiComment(value="删除风险隐患")
    @RequestMapping(name="删除风险隐患",value="/delete",method=RequestMethod.GET)
    public AlvesJSONResult delete(@ApiComment(value="风险隐患id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		throw new CustomException(ResultEnum.ERROR_PARAMETER.getNo(), "ids为空");
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
    		HazardSouce hazardSource = hazardSourceService.selectById(Integer.valueOf(id));
    		hazardSourceService.delete(hazardSource);
    	}
        return AlvesJSONResult.ok();
    }

    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询风险隐患")
    @RequestMapping(name="根据id查询风险隐患",value="/selectById",method=RequestMethod.GET)
    public AlvesJSONResult selectById(@ApiComment(value="风险隐患id",sample="1") Integer id) {
        HazardSouce hazardSource = hazardSourceService.selectById(id);
        return AlvesJSONResult.ok(hazardSource);
    }

    @Api2Doc(order = 5)
    @ApiComment(value="列出风险隐患")
    @RequestMapping(name="列出风险隐患",value="/list",method=RequestMethod.POST)
    public AlvesJSONResult list(@ApiComment(value="风险隐患参数",sample="根据id查询风险隐患接口可查看字段信息") @RequestBody HazardSouce hazardSouce) {
    	HazardSouceResult result = new HazardSouceResult();
        List<HazardSouce> list = hazardSourceService.queryForPage(hazardSouce, hazardSouce.getPage(), hazardSouce.getPageSize());
        for (HazardSouce hs : list) {
        	if (hs.getIcon() != null) {
        		hs.setCommonIcon(BaseObject.host + "/img/" + hs.getIcon() + "-common.png");
        		hs.setActiveIcon(BaseObject.host + "/img/" + hs.getIcon() + "-active.png");
        	}
        }
        Long count = hazardSourceService.queryForCounts(hazardSouce);
        result.setList(list);
        result.setCount(count);
        return AlvesJSONResult.ok(result);
    }
}
