package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.pojo.Group;
import com.kira.emercmdplat.pojo.Shift;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/23 11:37
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/contact")
public class ContactController extends BaseController {

    @Autowired
    private ContactService cs;

    @ResponseBody
    @PostMapping("add")
    public AlvesJSONResult insert(@Validated @RequestBody Contacts contacts) {
        int result = cs.insert(contacts);
        if (result > 0) {
            return AlvesJSONResult.ok("success insert...");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "新增联系人失败");
        }
    }

    @ResponseBody
    @PostMapping("add_group")
    public AlvesJSONResult insertGroup(@Validated @RequestBody Group group) {
        int result = cs.insertGroup(group);
        if (result > 0) {
            return AlvesJSONResult.ok("success insert...");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "新增分组失败");
        }
    }

    @ResponseBody
    @GetMapping("remove/{id}")
    public AlvesJSONResult delete(@PathVariable Long id) {
        boolean result = cs.delete(id);
        if (result) {
            return AlvesJSONResult.ok("success remove");
        } else {
            return AlvesJSONResult.errorMsg("fail remove...");
        }
    }

    @ResponseBody
    @GetMapping("remove_group/{id}")
    public AlvesJSONResult deleteGroup(@PathVariable Long id) {
        List<ContactsResult> contactsResultList = cs.selectByGid(id);
        if (contactsResultList != null || contactsResultList.size() > 0) {
            return AlvesJSONResult.errorMsg("分组下有联系人,无法删除");
        }
        boolean result = cs.deleteGroup(id);
        if (result) {
            return AlvesJSONResult.ok("success remove");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "删除分组失败");
        }
    }

    @ResponseBody
    @PostMapping("update")
    public AlvesJSONResult update(@RequestBody Contacts contacts) {
        boolean result = cs.update(contacts);
        if (result) {
            return AlvesJSONResult.ok("success update...");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "更新联系人失败");
        }
    }
    @ResponseBody
    @PostMapping("update_group")
    public AlvesJSONResult updateGroup(@RequestBody Group group) {
        boolean result = cs.updateGroup(group);
        if (result) {
            return AlvesJSONResult.ok("success update...");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "更新分组失败");
        }
    }
    @ResponseBody
    @GetMapping("contact/{id}")
    public AlvesJSONResult contact(@PathVariable Long id) {
        Contacts contacts = cs.selectById(id);
        return AlvesJSONResult.ok(contacts);
    }
    @ResponseBody
    @GetMapping("group/{id}")
    public AlvesJSONResult group(@PathVariable Long id) {
        Group group = cs.selectGroupById(id);
        return AlvesJSONResult.ok(group);
    }

    @ResponseBody
    @GetMapping("list_geo_contact")
    public AlvesJSONResult geoContacts() {
        List<ContactsResult> contactsResultList = cs.selectGeoContacts();
        return AlvesJSONResult.ok(contactsResultList);
    }
}
