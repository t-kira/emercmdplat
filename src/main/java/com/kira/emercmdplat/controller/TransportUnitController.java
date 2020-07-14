package com.kira.emercmdplat.controller;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.pojo.TransportUnit;
import com.kira.emercmdplat.pojo.TransportUnitResult;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.TransportUnitService;
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
@Api2Doc(id = "transportUnit", name = "运输单位接口", order = 7)
@RestController
@RequestMapping("/transportUnit")
public class TransportUnitController extends BaseController{

    @Autowired
    private TransportUnitService transportUnitService;

    @Autowired
	private ContactService contactService;

    @Api2Doc(order = 1)
    @ApiComment(value="添加运输单位")
    @RequestMapping(name="添加运输单位",value="/add",method=RequestMethod.POST)
    public String insert(@Validated @ApiComment(value="添加运输单位",sample="根据id查询运输单位接口可查看字段信息") @RequestBody TransportUnit transportUnit) {
    	Contacts contact = new Contacts();
    	contact.setTelephone(transportUnit.getCellNum());
    	List<ContactsResult> result = contactService.queryForAllOrPage(contact);
    	if (result != null && result.size() == 1) {
    		ContactsResult contactsResult = result.get(0);
    		transportUnit.setPIC(contactsResult.getContactName());
    		transportUnit.setContactsId(contactsResult.getId());
    	}
        transportUnitService.insert(transportUnit);
        return "success";
    }

    @Api2Doc(order = 2)
    @ApiComment(value="修改运输单位")
    @RequestMapping(name="修改运输单位",value="/update",method=RequestMethod.POST)
    public String update(@Validated @ApiComment(value="修改运输单位",sample="根据id查询运输单位接口可查看字段信息") @RequestBody TransportUnit transportUnit) {
        transportUnitService.update(transportUnit);
        return "success";
    }

    @Api2Doc(order = 3)
    @ApiComment(value="删除运输单位")
    @RequestMapping(name="删除运输单位",value="/delete",method=RequestMethod.GET)
    public String delete(@ApiComment(value="储备库id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		return "fail";
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
	    	TransportUnit transportUnit = transportUnitService.selectById(Integer.valueOf(id));
	        transportUnitService.delete(transportUnit);
    	}
        return "success";
    }

    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询运输单位")
    @RequestMapping(name="根据id查询运输单位",value="/selectById",method=RequestMethod.GET)
    public TransportUnit selectById(@ApiComment(value="运输单位id",sample="1") Integer id) {
        TransportUnit transportUnit = transportUnitService.selectById(id);
        return transportUnit;
    }

    @Api2Doc(order = 5)
    @ApiComment(value="列出运输单位")
    @RequestMapping(name="列出运输单位",value="/list",method=RequestMethod.POST)
    public TransportUnitResult list(@ApiComment(value="列出运输单位",sample="根据id查询运输单位接口可查看字段信息") @RequestBody TransportUnit transportUnit) {
    	TransportUnitResult result = new TransportUnitResult();
        List<TransportUnit> list = transportUnitService.queryForPage(transportUnit, transportUnit.getPage(), transportUnit.getPageSize());
        Long count = transportUnitService.queryForCounts(transportUnit);
        result.setList(list);
        result.setCount(count);
        return result;
    }
}
