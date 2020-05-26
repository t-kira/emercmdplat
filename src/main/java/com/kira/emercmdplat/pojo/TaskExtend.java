package com.kira.emercmdplat.pojo;

import lombok.Data;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/9 23:59
 * @Description:
 */
@Data
public class TaskExtend extends Task{

    private List<Long> contactIdList;

    private List<JSONObject> contactList;

    private Integer page;

    private Integer pageSize;

    private String order;

    private String orderType;

    public TaskExtend() {
        super();
    }
}
