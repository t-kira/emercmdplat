package com.kira.emercmdplat.config;

import com.kira.emercmdplat.enums.BaseDataType;
import com.kira.emercmdplat.pojo.BaseData;
import java.util.*;

/**
 * @author kira
 * @version V1.0
 * @创建时间 基础时间初始化
 * @备注
 * @类名
 * @节点
 */
public class InitData {

    public static final Map<String, String> base_map = new HashMap<String, String>();

    public static String getVal(Integer code) {
        String key = BaseDataType.getByValue(code).getName();

        if (base_map.containsKey(key)) {
            return base_map.get(key);
        }
        return null;
    }
    public static void putVal(List<BaseData> list) {
        for (BaseData baseData : list) {
            String key = BaseDataType.getByValue(baseData.getType()).getName();
            base_map.put(key, baseData.getBasicData());
        }
    }
}
