package com.kira.emercmdplat.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/23 19:09
 * @Description:任务指派
 */
@Data
public class TaskAssignment {
    /**
     * 指派对象ID集合
     */
    private List<Integer> idList;
    /**
     * 指派具体类型
     */
    private Integer type;
}
