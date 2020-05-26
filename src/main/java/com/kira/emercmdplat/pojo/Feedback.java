package com.kira.emercmdplat.pojo;

import lombok.Data;
import net.sf.json.JSONArray;

/**
 * @Author: kira
 * @Date: 2020/5/22 23:05
 * @Description:事件任务反馈信息
 */
@Data
public class Feedback {

    private Long id;
    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 经度
     */
    private Double lng;
    /**
     * 事件位置
     */
    private String address;
    /**
     * 纬度
     */
    private Double lat;
    /**
     * 反馈内容
     */
    private String feedbackContent;
    /**
     * 附件地址
     */
    private String attachPath;
    /**
     * 反馈时间
     */
    private String feedbackTime;
}
