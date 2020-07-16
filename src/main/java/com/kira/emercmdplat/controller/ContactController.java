package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        contacts.setCreateTime(DateUtil.getNowStr());
        int result = cs.insert(contacts);
        if (result > 0) {
            return AlvesJSONResult.ok("联系人添加成功");
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
    @GetMapping("remove/{contactId}")
    public AlvesJSONResult delete(@PathVariable Long contactId) {
        boolean result = cs.delete(contactId);
        if (result) {
            return AlvesJSONResult.ok("success remove");
        } else {
            return AlvesJSONResult.errorMsg("fail remove...");
        }
    }

    @ResponseBody
    @GetMapping("remove_group/{groupId}")
    public AlvesJSONResult deleteGroup(@PathVariable Long groupId) {
        List<ContactsResult> contactsResultList = cs.selectByGid(groupId);
        if (contactsResultList != null && contactsResultList.size() > 0) {
        	return AlvesJSONResult.errorMsg("分组下有联系人,无法删除");
        } else {
        	boolean result = cs.deleteGroup(groupId);
            if (result) {
                return AlvesJSONResult.ok("success remove");
            } else {
                throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "删除分组失败");
            }
        }
    }

    @ResponseBody
    @GetMapping(name = "根据分组信息查看联系人",value = "list_group_contact/{groupId}")
    public AlvesJSONResult selectContactListByGroup(@PathVariable Long groupId) {
        List<ContactsResult> contactsResultList = cs.selectByGid(groupId);
        if (contactsResultList != null && contactsResultList.size() > 0) {
            return AlvesJSONResult.ok(contactsResultList);
        } else {
            throw new CustomException(ResultEnum.NON_DATA.getNo(), "该分组没有联系人信息");
        }
    }

    @ResponseBody
    @PostMapping("update")
    public AlvesJSONResult update(@RequestBody Contacts contacts) {
        if (contacts.getId() == null || contacts.getId() <= 0) {
            throw new CustomException(ResultEnum.MISSING_PARAMETER.getNo(), "联系人ID不能为空");
        }
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
        if (group.getId() == null || group.getId() <= 0) {
            throw new CustomException(ResultEnum.MISSING_PARAMETER.getNo(), "分组ID不能为空");
        }
        boolean result = cs.updateGroup(group);
        if (result) {
            return AlvesJSONResult.ok("success update...");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "更新分组失败");
        }
    }
    @ResponseBody
    @GetMapping("contact/{contactId}")
    public AlvesJSONResult contact(@PathVariable Long contactId) {
        Contacts contacts = cs.selectById(contactId);
        return AlvesJSONResult.ok(contacts);
    }
    @ResponseBody
    @GetMapping("group/{groupId}")
    public AlvesJSONResult group(@PathVariable Long groupId) {
        Group group = cs.selectGroupById(groupId);
        return AlvesJSONResult.ok(group);
    }

    @ResponseBody
    @GetMapping("list_geo_contact")
    public AlvesJSONResult geoContacts() {
        List<ContactsResult> contactsResultList = cs.selectGeoContacts();
        return AlvesJSONResult.ok(contactsResultList);
    }

    @ResponseBody
    @PostMapping(name="分页查看联系人集合", value = "list")
    public AlvesJSONResult list(@RequestBody Contacts contacts) {
        Map<String, Object> map = new HashMap<>();
        List<ContactsResult> list = cs.queryForAllOrPage(contacts);
        Long count = cs.queryForCounts(contacts);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }

    @ResponseBody
    @PostMapping(name="分页查看联系人分组集合", value = "list_group")
    public AlvesJSONResult listGroup(@RequestBody Group group) {
        Map<String, Object> map = new HashMap<>();
        List<Group> list = cs.selectGroup(group);
        Long count = cs.queryForGroupCounts(group);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }

    @ResponseBody
    @PostMapping(name="查看角色集合", value = "list_role")
    public AlvesJSONResult listRole(@RequestBody Role role) {
        List<Role> list = cs.queryRoleForAllOrPage(role);
        return AlvesJSONResult.ok(list);
    }
}
