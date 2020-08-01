package com.kira.emercmdplat.pojo;

import lombok.Data;

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

    private Integer mediaType;
}
