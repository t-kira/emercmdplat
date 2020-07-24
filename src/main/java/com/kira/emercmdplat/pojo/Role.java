package com.kira.emercmdplat.pojo;

import lombok.Data;
import net.sf.json.JSONObject;

/**
 * @Author: kira
 * @Date: 2020/7/16 13:27
 * @Description:角色
 */
@Data
public class Role extends Base{

    private Long id;

    private String roleName;

    public static void main(String[] args) {
        JSONObject jsonObject = JSONObject.fromObject(new Role());
        System.out.println(jsonObject.toString());
    }
}
