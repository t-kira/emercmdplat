package com.kira.emercmdplat.pojo;

import lombok.Data;
import net.sf.json.JSONObject;

/**
 * @Author: kira
 * @Date: 2020/8/1 12:45
 * @Description:音视频资源
 */
@Data
public class Media {

    private Long id;

    private Long feedbackId;

    private String mediaUrl;
    /**
     * 多媒体类型 1：视频 2 音频
     */
    private Integer mediaType;

    public static void main(String[] args) {
        System.out.println(JSONObject.fromObject(new Media()));
    }
}
