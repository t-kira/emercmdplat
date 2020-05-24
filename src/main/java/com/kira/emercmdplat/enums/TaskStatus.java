package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/4/13 02:33
 * @Description:事件任务状态
 */
public enum TaskStatus implements BaseEnum<TaskStatus> {

    TASK_PENDING(1, "待处理"), TASK_PROCESSING(2, "处理中"),
    TASK_PROCESSED(3, "已处理");

    private Integer TASK_CODE;

    private String TASK_NAME;

    TaskStatus(Integer code, String name) {
        this.TASK_CODE = code;
        this.TASK_NAME = name;
    }

    /**
     * 获取属性int值
     *
     * @return
     */
    @Override
    public Integer getNo() {
        return TASK_CODE;
    }

    /**
     * 获取属性名称
     *
     * @return
     */
    @Override
    public String getName() {
        return TASK_NAME;
    }

    /**
     * 枚举值
     * @param key
     * @return
     */
    @Override
    public TaskStatus getProperty(Integer key) {
        switch (key) {
            case 1:
                return TASK_PENDING;
            case 2:
                return TASK_PROCESSING;
            case 3:
                return TASK_PROCESSED;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
