package com.kira.emercmdplat.controller;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.pojo.ReserveLibrary;
import com.kira.emercmdplat.pojo.ReserveLibraryResult;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.ReserveLibraryService;
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
@Api2Doc(id = "reserveLibrary", name = "储备库接口", order = 6)
@RestController
@RequestMapping("/reserveLibrary")
public class ReserveLibraryController extends BaseController {

    @Autowired
    private ReserveLibraryService reserveLibraryService;

    @Autowired
	private ContactService contactService;

    @Api2Doc(order = 1)
    @ApiComment(value="添加储备库")
    @RequestMapping(name="添加储备库",value="/add",method=RequestMethod.POST)
    public String insert(@Validated @ApiComment(value="添加储备库",sample="根据id查询储备库接口可查看字段信息") @RequestBody ReserveLibrary reserveLibrary) {
    	Contacts contact = new Contacts();
    	contact.setTelephone(reserveLibrary.getCellNum());
    	List<ContactsResult> result = contactService.queryForAllOrPage(contact);
    	if (result != null && result.size() == 1) {
    		ContactsResult contactsResult = result.get(0);
    		reserveLibrary.setPIC(contactsResult.getContactName());
    		reserveLibrary.setContactsId(contactsResult.getId());
    	}
        reserveLibraryService.insert(reserveLibrary);
        return "success";
    }

    @Api2Doc(order = 2)
    @ApiComment(value="修改储备库")
    @RequestMapping(name="修改储备库",value="/update",method=RequestMethod.POST)
    public String update(@Validated @ApiComment(value="修改储备库",sample="根据id查询储备库接口可查看字段信息") @RequestBody ReserveLibrary reserveLibrary) {
        reserveLibraryService.update(reserveLibrary);
        return "success";
    }

    @Api2Doc(order = 3)
    @ApiComment(value="删除储备库")
    @RequestMapping(name="删除储备库",value="/delete",method=RequestMethod.GET)
    public String delete(@ApiComment(value="储备库id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		return "fail";
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
	    	ReserveLibrary reserveLibrary = reserveLibraryService.selectById(Integer.valueOf(id));
	        reserveLibraryService.delete(reserveLibrary);
    	}
        return "success";
    }

    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询储备库")
    @RequestMapping(name="根据id查询储备库",value="/selectById",method=RequestMethod.GET)
    public ReserveLibrary selectById(@ApiComment(value="储备库id",sample="1") Integer id) {
        ReserveLibrary reserveLibrary = reserveLibraryService.selectById(id);
        return reserveLibrary;
    }

    @Api2Doc(order = 5)
    @ApiComment(value="列出储备库")
    @RequestMapping(name="列出储备库",value="/list",method=RequestMethod.POST)
    public ReserveLibraryResult list(@ApiComment(value="列出储备库",sample="根据id查询储备库接口可查看字段信息") @RequestBody ReserveLibrary reserveLibrary) {
    	ReserveLibraryResult result = new ReserveLibraryResult();
        List<ReserveLibrary> list = reserveLibraryService.queryForPage(reserveLibrary, reserveLibrary.getPage(), reserveLibrary.getPageSize());
        Long count = reserveLibraryService.queryForCounts(reserveLibrary);
        result.setList(list);
        result.setCount(count);
        return result;
    }
}
