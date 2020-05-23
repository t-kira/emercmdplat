package com.kira.emercmdplat.utils;

import com.kira.emercmdplat.pojo.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/7 23:30
 * @Description:
 */
public class TreeUtil {

    public static List<Group> treeRecursionDataList(List<Group> treeList, long parentId) {
        List<Group> list = new ArrayList<>();
        for (Group group : treeList) {
            long id = group.getId();
            long superGid = group.getSuperGid();
            if (superGid == parentId) {
                List<Group> gList = treeRecursionDataList(treeList, id);
                group.setGroupList(gList);
                list.add(group);
            }
        }
        return list;
    }
}