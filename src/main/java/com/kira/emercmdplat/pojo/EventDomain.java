package com.kira.emercmdplat.pojo;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/7 00:05
 * @Description:
 */
public class EventDomain {

    private Event event;

    private List<EventParam> eventParamList;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<EventParam> getEventParamList() {
        return eventParamList;
    }

    public void setEventParamList(List<EventParam> eventParamList) {
        this.eventParamList = eventParamList;
    }
}
