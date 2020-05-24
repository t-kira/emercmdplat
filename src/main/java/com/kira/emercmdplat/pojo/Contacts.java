package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;
import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/21 22:26
 * @Description:
 */
@Data
public class Contacts {

    private Long id;
    @ApiComment(value = "姓名", sample = "张三")
    private String contactName;
    /**
     * 性别 0：男 非0：女
     */
    @ApiComment(value = "性别", sample = "0")
    private Integer gender;
    @ApiComment(value = "职务ID", sample = "1")
    private Long jId;
    @ApiComment(value = "工作单位ID", sample = "1")
    private Long mId;
    @ApiComment(value = "手机号码", sample = "18223457654")
    private String telephone;
    @ApiComment(value = "备用手机", sample = "17523126754")
    private String backupPhone;
    @ApiComment(value = "手持终端", sample = "iphone xs")
    private String terminal;
    @ApiComment(value = "办公电话", sample = "0571-86432347")
    private String workPhone;
    @ApiComment(value = "家庭电话", sample = "0571-85463242")
    private String homePhone;
    @ApiComment(value = "其他电话", sample = "15623749876")
    private String otherPhone;
    @ApiComment(value = "传真号码", sample = "0571-86574327")
    private String faxNumber;
    @ApiComment(value = "电子邮箱", sample = "kk@126.com")
    private String email;
    /**
     * 0：一般 1：紧急
     */
    @ApiComment(value = "重要程度", sample = "0")
    private Integer importanceDegree;
    @ApiComment(value = "照片地址", sample = "/usr/static/2.jpg")
    private String photo;
    @ApiComment(value = "备注", sample = "这是一条备注信息")
    private String notes;
    @ApiComment(value = "分组ID", sample = "1")
    private Long gId;
    @ApiComment(value = "纬度", sample = "23.0001")
    private Double lat;
    @ApiComment(value = "经度", sample = "23.0001")
    private Double lng;
    @ApiComment(value = "添加时间", sample = "2020-04-21 22:44:40")
    private String createTime;
    @ApiComment(value = "添加时间", sample = "2020-04-22 22:44:40")
    private String updateTime;
    /**
     * 0：非值班人员 1：值班人员
     */
    @ApiComment(value = "人物属性", sample = "0")
    private Integer personAttribute;
    @ApiComment(value = "登录用户名", sample = "aaa")
    private String username;
    @ApiComment(value = "登录密码", sample = "123")
    private String password;

}
