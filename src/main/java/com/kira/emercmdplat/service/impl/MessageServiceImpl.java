package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.MessageMapper;
import com.kira.emercmdplat.pojo.Message;
import com.kira.emercmdplat.pojo.MessageExtend;
import com.kira.emercmdplat.pojo.MessageResult;
import com.kira.emercmdplat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/18 22:36
 * @Description:
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper mm;
    /**
     * 增/插入数据
     *
     * @param message
     * @return
     */
    @Override
    public int insert(Message message) {
        return mm.insert(message);
    }

    /**
     * 删（其实就是修改了状态，使其不显示，在客户端看来是删除了）
     *
     * @param message
     * @return
     */
    @Override
    public boolean delete(Message message) {
        return mm.delete(message);
    }

    /**
     * 改，
     *
     * @param message
     * @return
     */
    @Override
    public boolean update(Message message) {
        return mm.update(message);
    }

    /**
     * 根据id查询单个
     *
     * @param id
     * @return
     */
    @Override
    public MessageResult selectById(Long id) {
        return mm.selectById(id);
    }

    /**
     * 按条件查询
     *
     * @param messageExtend
     * @return
     */
    @Override
    public List<MessageResult> queryForAll(MessageExtend messageExtend) {
        return mm.queryForAll(messageExtend);
    }

    @Override
    public List<MessageResult> queryForPage(MessageExtend messageExtend) {
        return mm.queryForPage(messageExtend);
    }

    @Override
    public Long queryForCounts(MessageExtend messageExtend) {
        return mm.queryForCounts(messageExtend);
    }
}
