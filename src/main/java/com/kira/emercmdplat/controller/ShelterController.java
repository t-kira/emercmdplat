package com.kira.emercmdplat.controller;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.pojo.Shelter;
import com.kira.emercmdplat.pojo.ShelterResult;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.ShelterService;
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
@Api2Doc(id = "shelter", name = "避难场所接口", order = 13)
@RestController
@RequestMapping("/shelter")
public class ShelterController extends BaseController {

    @Autowired
    private ShelterService shelterService;

    @Autowired
	private ContactService contactService;

    @Api2Doc(order = 1)
    @ApiComment(value="添加避难场所")
    @RequestMapping(name="添加避难场所",value="/add",method=RequestMethod.POST)
    public String insert(@Validated @ApiComment(value="添加避难场所",sample="根据id查询避难场所接口可查看字段信息") @RequestBody Shelter shelter) {
    	Contacts contact = new Contacts();
    	contact.setTelephone(shelter.getCellNum());
    	List<ContactsResult> result = contactService.queryForAll(contact);
    	if (result != null && result.size() == 1) {
    		ContactsResult contactsResult = result.get(0);
    		shelter.setPIC(contactsResult.getContactName());
    		shelter.setContactsId(contactsResult.getId());
    	}
        shelterService.insert(shelter);
        return "success";
    }

    @Api2Doc(order = 2)
    @ApiComment(value="修改避难场所")
    @RequestMapping(name="修改避难场所",value="/update",method=RequestMethod.POST)
    public AlvesJSONResult update(@Validated @ApiComment(value="修改避难场所",sample="根据id查询避难场所接口可查看字段信息") @RequestBody Shelter shelter) {
        shelterService.update(shelter);
        return AlvesJSONResult.ok();
    }

    @Api2Doc(order = 3)
    @ApiComment(value="删除避难场所")
    @RequestMapping(name="删除防护目标",value="/delete",method=RequestMethod.GET)
    public String delete(@ApiComment(value="防护目标id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		return "fail";
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
    		Shelter shelter = shelterService.selectById(Integer.valueOf(id));
    		shelterService.delete(shelter);
    	}
        return "success";
    }

    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询避难场所")
    @RequestMapping(name="根据id查询避难场所",value="/selectById",method=RequestMethod.GET)
    public Shelter selectById(@ApiComment(value="避难场所id",sample="1") Integer id) {
        Shelter shelter = shelterService.selectById(id);
        return shelter;
    }

    @Api2Doc(order = 5)
    @ApiComment(value="列出避难场所")
    @RequestMapping(name="列出避难场所",value="/list",method=RequestMethod.POST)
    public ShelterResult list(@ApiComment(value="避难场所参数",sample="根据id查询避难场所接口可查看字段信息") @RequestBody Shelter shelter) {
    	ShelterResult result = new ShelterResult();
        List<Shelter> list = shelterService.queryForPage(shelter, shelter.getPage(), shelter.getPageSize());
        Long count = shelterService.queryForCounts(shelter);
        result.setList(list);
        result.setCount(count);
        return result;
    }
}
