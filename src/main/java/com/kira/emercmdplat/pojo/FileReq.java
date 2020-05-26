package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/5/26 14:10
 * @Description:
 */
@Data
public class FileReq {
    /**
     * 文件后缀
     */
    private String extension;
    /**
     * 文件base64 内容
     */
    private String fileContent;
}
