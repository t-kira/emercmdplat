package com.kira.emercmdplat.pojo;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @Author: kira
 * @Date: 2020/7/24 11:16
 * @Description:坐标点
 */
@Data
public class Point extends Base{

    private Long id;
    /**
     * 资源ID
     */
    @NotNull(message = "资源ID不能为空")
    private Long resourceId;
    /**
     * 纬度
     */
    private Double lat;
    /**
     * 经度
     */
    private Double lng;
    /**
     * 上报时间
     */
    private Long reportTimeStamp;

    public static void main(String[] args) {
        int random = (int)(1 + Math.random() * (10 -1 + 1));
        System.out.println(random/100.0);
    }
}
