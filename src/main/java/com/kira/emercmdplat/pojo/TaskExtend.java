package com.kira.emercmdplat.pojo;

import lombok.Data;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/9 23:59
 * @Description:
 */
@Data
public class TaskExtend extends Task{

    private List<Long> contactIdList;

    private List<JSONObject> contactList;

    public TaskExtend() {
        super();
    }
}
