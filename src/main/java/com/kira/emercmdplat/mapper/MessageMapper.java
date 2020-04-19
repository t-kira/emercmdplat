package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.Message;
import com.kira.emercmdplat.pojo.MessageExtend;
import com.kira.emercmdplat.pojo.MessageResult;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/17 23:29
 * @Description:
 */
public interface MessageMapper {

    /**
     * 增/插入数据
     * @param message
     * @return
     */
    int insert(Message message);

    /**
     * 删（其实就是修改了状态，使其不显示，在客户端看来是删除了）
     * @param message
     * @return
     */
    boolean delete(Message message);

    /**
     * 改，
     * @param message
     * @return
     */
    boolean update(Message message);

    /**
     * 根据id查询单个
     *
     * @param id
     * @return
     */
    MessageResult selectById(Long id);

    /**
     * 按条件查询
     * @param messageExtend
     * @return
     */
    List<MessageResult> queryForAll(MessageExtend messageExtend);

    List<MessageResult> queryForPage(MessageExtend messageExtend);

    Long queryForCounts(MessageExtend messageExtend);
}
