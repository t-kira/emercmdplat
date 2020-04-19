package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.MessageStatus;
import com.kira.emercmdplat.enums.MessageType;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.MessageService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/18 22:38
 * @Description:
 */
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService ms;

    /**
     * 新增消息
     * @param message
     * @return
     */
    public AlvesJSONResult insert(@RequestBody Message message) {
        int result = ms.insert(message);
        if (result > 0) {
            return AlvesJSONResult.ok("success insert message...");
        } else {
            return AlvesJSONResult.errorMsg("error insert message...");
        }
    }

    /**
     * 删除消息
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping(value = "delete/{id}")
    public AlvesJSONResult delete(@PathVariable Long id) {
        Message message = new Message();
        message.setId(id);
        message.setStatus(MessageStatus.MESSAGE_DELETE.getNo());
        boolean result = ms.update(message);
        if (result) {
            return AlvesJSONResult.ok("success update message...");
        } else {
            return AlvesJSONResult.errorMsg("error update message...");
        }
    }

    /**
     * 修改消息
     * @param message
     * @return
     */
    @ResponseBody
    @PostMapping(value = "update")
    public AlvesJSONResult update(@RequestBody Message message) {
        boolean result = ms.update(message);
        if (result) {
            if (message.getType() == MessageType.MESSAGE_TRANSFER.getNo() || message.getType() == MessageType.MESSAGE_REPORT.getNo()) {
                message.setStatus(MessageStatus.MESSAGE_UNREAD.getNo());
                message.setDid(message.getToDid());
                message.setToDid(null);
                message.setId(null);
                ms.insert(message);
            }
            return AlvesJSONResult.ok("success update message...");
        } else {
            return AlvesJSONResult.errorMsg("error update message...");
        }
    }

    @ResponseBody
    @PostMapping("list")
    public AlvesJSONResult list(@RequestBody MessageExtend messageExtend) {
        Map<String, Object> map = new HashMap<>();
        List<MessageResult> list = ms.queryForPage(messageExtend);
        Long count = ms.queryForCounts(messageExtend);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
