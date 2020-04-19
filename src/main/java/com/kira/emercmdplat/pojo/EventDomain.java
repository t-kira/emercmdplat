package com.kira.emercmdplat.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/7 00:05
 * @Description:
 */
@Data
public class EventDomain {

    private Event event;

    private List<EventParam> eventParamList;
}
