package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/6 13:47
 * @Description:
 */
@Data
public class Duty {

    private Long id;
    /**
     * 值班人员姓名
     */
    private String name;
    /**
     * 性别 0：男 非0：女
     */
    private Integer gender;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 所属机构ID
     */
    private Long mId;
    /**
     * 职务id
     */
    private Long jId;
    /**
     * 联系电话
     */
    private String contactNum;
    /**
     * 手机号码
     */
    private String cellNum;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 传真号码
     */
    private String faxNum;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;

}
