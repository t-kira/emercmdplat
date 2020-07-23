package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.annotation.MyLog;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.*;
import com.kira.emercmdplat.exception.CustomException;
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
                message.setContactId(message.getToContactId());
                message.setToContactId(null);
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
    @MyLog(value = 4)
    @ResponseBody
    @PostMapping(value = "add_leader_instruct")
    public AlvesJSONResult insertLeaderInstruct(@RequestBody LeaderInstructExtend leaderInstructExtend) {
        EventResult eventResult = es.selectById(leaderInstructExtend.getEventId());
        if (eventResult == null) {
            throw new CustomException(ResultEnum.NON_DATA.getNo());
        }
        if (eventResult.getStatus() == EventStatus.FINISH.getNo() && eventResult.getProcess() == EventProcess.EVENT_FINISH.getNo()) {
            throw new CustomException(ResultEnum.EVENT_FINISH.getNo());
        }
        leaderInstructExtend.setInstructStatus(0);
        leaderInstructExtend.setInstructTime(DateUtil.getNowStr());
        int result = lis.insert(leaderInstructExtend);
        if (result > 0) {
            Event event = new Event();
            event.setId(leaderInstructExtend.getEventId());
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
    public AlvesJSONResult list(@RequestBody(required = false) Message message, HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cs.findByToken(token);
        message.setContactId(contactsResult.getId());
        List<MessageResult> list = ms.queryForAllOrPage(message);
        Long count = ms.queryForCounts(message);
        return AlvesJSONResult.pageOk(list, count);
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
