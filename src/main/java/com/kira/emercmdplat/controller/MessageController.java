package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.annotation.MyLog;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.EventProcess;
import com.kira.emercmdplat.enums.MessageStatus;
import com.kira.emercmdplat.enums.MessageType;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.EventService;
import com.kira.emercmdplat.service.LeaderInstructService;
import com.kira.emercmdplat.service.MessageService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.DateUtil;
import com.kira.emercmdplat.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private LeaderInstructService lis;
    @Autowired
    private EventService es;
    @Autowired
    private ContactService cs;

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

    /**
     * 添加领导批示
     * @param leaderInstructExtend
     * @return
     */
    @MyLog("拟办意见")
    @ResponseBody
    @PostMapping(value = "add_leader_instruct")
    public AlvesJSONResult insertLeaderInstruct(@RequestBody LeaderInstructExtend leaderInstructExtend) {
        leaderInstructExtend.setInstructStatus(0);
        leaderInstructExtend.setInstructTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
        int result = lis.insert(leaderInstructExtend);
        if (result > 0) {
            Event event = new Event();
            event.setId(leaderInstructExtend.getEid());
            event.setProcess(EventProcess.LEADER_INSTRUCT.getNo());
            es.update(event);
            //修改消息状态
            Message message = new Message();
            message.setId(leaderInstructExtend.getMid());
            message.setStatus(MessageStatus.MESSAGE_READ.getNo());
            ms.update(message);
            return AlvesJSONResult.ok(EventProcess.LEADER_INSTRUCT.getNo());
        } else {
            return AlvesJSONResult.errorMsg("fail insert leader instruct...");
        }
    }

    @ResponseBody
    @PostMapping("list")
    public AlvesJSONResult list(@RequestBody MessageExtend messageExtend, HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cs.findByToken(token);
        messageExtend.setDid(contactsResult.getId());
        Map<String, Object> map = new HashMap<>();
        List<MessageResult> list = ms.queryForPage(messageExtend);
        Long count = ms.queryForCounts(messageExtend);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }

    @ResponseBody
    @GetMapping("message/{id}")
    public AlvesJSONResult selectById(@PathVariable Long id) {
         MessageResult messageResult = ms.selectById(id);
         if (messageResult != null) {
             return AlvesJSONResult.ok(messageResult);
         } else {
             return AlvesJSONResult.errorMsg("message is not exist...");
         }
    }
}
