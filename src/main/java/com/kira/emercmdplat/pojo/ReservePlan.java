package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/13 17:18
 * @Description:
 */
@Data
public class ReservePlan {

    private Long id;

    private Long eventId;

    private String startTime;

    private Integer status;
}
