package com.kira.emercmdplat.controller;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.pojo.MedicalInstitution;
import com.kira.emercmdplat.pojo.MedicalInstitutionResult;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.MedicalInstitutionService;
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
@Api2Doc(id = "medicalInstitution", name = "医疗机构接口", order = 11)
@RestController
@RequestMapping("/medicalInstitution")
public class MedicalInstitutionController extends BaseController {

    @Autowired
    private MedicalInstitutionService medicalInstitutionService;

    @Autowired
	private ContactService contactService;

    @Api2Doc(order = 1)
    @ApiComment(value="添加医疗机构")
    @RequestMapping(name="添加医疗机构",value="/add",method=RequestMethod.POST)
    public AlvesJSONResult insert(@Validated @ApiComment(value="添加医疗机构",sample="根据id查询医疗机构接口可查看字段信息") @RequestBody MedicalInstitution medicalInstitution) {
    	Contacts contact = new Contacts();
    	contact.setTelephone(medicalInstitution.getCellNum());
    	List<ContactsResult> result = contactService.queryForAllOrPage(contact);
    	if (result != null && result.size() == 1) {
    		ContactsResult contactsResult = result.get(0);
    		medicalInstitution.setPIC(contactsResult.getContactName());
    		medicalInstitution.setContactsId(contactsResult.getId());
    	}
    	//唯一识别码
    	MedicalInstitution m = new MedicalInstitution();
    	m.setUID(medicalInstitution.getUID());
    	Long count = medicalInstitutionService.queryForCounts(m);
    	if (count > 0) {
    		throw new CustomException(ResultEnum.ERROR_PARAMETER.getNo(), "医疗机构唯一识别码不能重复");
    	}
    	int c = medicalInstitutionService.insert(medicalInstitution);
    	if (c > 0) {
    		return AlvesJSONResult.ok();
    	} else {
    		throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "医疗机构保存失败");
    	}
    }

    @Api2Doc(order = 2)
    @ApiComment(value="修改医疗机构")
    @RequestMapping(name="修改医疗机构",value="/update",method=RequestMethod.POST)
    public AlvesJSONResult update(@Validated @ApiComment(value="添加医疗机构",sample="根据id查询医疗机构接口可查看字段信息") @RequestBody MedicalInstitution medicalInstitution) {
    	//唯一识别码
    	Long count = medicalInstitutionService.queryForCountsForUID(medicalInstitution);
    	if (count > 0) {
    		throw new CustomException(ResultEnum.ERROR_PARAMETER.getNo(), "医疗机构唯一识别码不能重复");
    	}
    	boolean result = medicalInstitutionService.update(medicalInstitution);
        if (result) {
        	return AlvesJSONResult.ok();
        } else {
        	throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "医疗机构保存失败");
        }
    }

    @Api2Doc(order = 3)
    @ApiComment(value="删除医疗机构")
    @RequestMapping(name="删除医疗机构",value="/delete",method=RequestMethod.GET)
    public AlvesJSONResult delete(@ApiComment(value="医疗机构id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		throw new CustomException(ResultEnum.ERROR_PARAMETER.getNo(), "ids为空");
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
    		MedicalInstitution medicalInstitution = medicalInstitutionService.selectById(Integer.valueOf(id));
    		medicalInstitutionService.delete(medicalInstitution);
    	}
        return AlvesJSONResult.ok();
    }

    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询医疗机构")
    @RequestMapping(name="根据id查询医疗机构",value="/selectById",method=RequestMethod.GET)
    public AlvesJSONResult selectById(@ApiComment(value="医疗机构id",sample="1") Integer id) {
        MedicalInstitution medicalInstitution = medicalInstitutionService.selectById(id);
        return AlvesJSONResult.ok(medicalInstitution);
    }

    @Api2Doc(order = 5)
    @ApiComment(value="列出医疗机构")
    @RequestMapping(name="列出医疗机构",value="/list",method=RequestMethod.POST)
    public AlvesJSONResult list(@ApiComment(value="医疗机构参数",sample="根据id查询医疗机构接口可查看字段信息") @RequestBody MedicalInstitution medicalInstitution) {
    	MedicalInstitutionResult result = new MedicalInstitutionResult();
        List<MedicalInstitution> list = medicalInstitutionService.queryForPage(medicalInstitution, medicalInstitution.getPage(), medicalInstitution.getPageSize());
        Long count = medicalInstitutionService.queryForCounts(medicalInstitution);
        result.setList(list);
        result.setCount(count);
        return AlvesJSONResult.ok(result);
    }
}
