package com.kira.emercmdplat.pojo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/7 00:05
 * @Description:
 */
@Data
public class EventDomain {
    @Valid
    @NotNull(message = "事件不能为空")
    private Event event;

    private List<EventParam> eventParamList;
}
