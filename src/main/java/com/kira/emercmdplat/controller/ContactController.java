package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.pojo.EventTask;
import com.kira.emercmdplat.pojo.Group;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.TreeUtil;
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
public class ContactController {

    private ContactService cs;

    @ResponseBody
    @PostMapping("add")
    public AlvesJSONResult insert(@RequestBody Contacts contacts) {
        int result = cs.insert(contacts);
        if (result > 0) {
            return AlvesJSONResult.ok("success insert...");
        } else {
            return AlvesJSONResult.errorMsg("fail insert...");
        }
    }

    @ResponseBody
    @PostMapping("add_group")
    public AlvesJSONResult insertGroup(@RequestBody Group group) {
        int result = cs.insertGroup(group);
        if (result > 0) {
            return AlvesJSONResult.ok("success insert...");
        } else {
            return AlvesJSONResult.errorMsg("fail insert...");
        }
    }

    @ResponseBody
    @GetMapping("remove")
    public AlvesJSONResult delete(@PathVariable Long id) {
        boolean result = cs.delete(id);
        if (result) {
            return AlvesJSONResult.ok("success remove");
        } else {
            return AlvesJSONResult.errorMsg("fail remove...");
        }
    }

    @ResponseBody
    @GetMapping("remove_group")
    public AlvesJSONResult deleteGroup(@PathVariable Long id) {
        boolean result = cs.deleteGroup(id);
        if (result) {
            return AlvesJSONResult.ok("success remove");
        } else {
            return AlvesJSONResult.errorMsg("fail remove...");
        }
    }

    @ResponseBody
    @PostMapping("update")
    public AlvesJSONResult update(@RequestBody Contacts contacts) {
        boolean result = cs.update(contacts);
        if (result) {
            return AlvesJSONResult.ok("success update...");
        } else {
            return AlvesJSONResult.errorMsg("fail update...");
        }
    }
    @ResponseBody
    @PostMapping("update_group")
    public AlvesJSONResult updateGroup(@RequestBody Group group) {
        boolean result = cs.updateGroup(group);
        if (result) {
            return AlvesJSONResult.ok("success update...");
        } else {
            return AlvesJSONResult.errorMsg("fail update...");
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
