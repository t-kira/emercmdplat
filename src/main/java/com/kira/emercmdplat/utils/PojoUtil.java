package com.kira.emercmdplat.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/***
 * pojo 工具类
 * 将pojo对象转成map
 *
 */

public final class PojoUtil {

    /**
     * @param pojo
     * @param index    页数
     * @param pageSize 每页条数
     * @return
     */
    public static Map<String, Object> pojoToMap(Object pojo, Integer index, Integer pageSize) {
        Map map = pojoToMap(pojo, true);// LIMIT #{pageIndex},#{pageSize}
        map.put("index", (index - 1) * pageSize);
        map.put("pageSize", pageSize);
        return map;
    }


    public static Map<String, Object> pojoToMap(Object pojo, boolean ignore) {

        Map map = new HashMap();

        if (pojo != null) {

            Class clazz = pojo.getClass();

            while ((clazz != null) && (clazz != Object.class)) {

                Field[] fields = clazz.getDeclaredFields();

                fields2Map(pojo, fields, map, ignore);

                clazz = clazz.getSuperclass();

            }

        }

        return map;

    }


    private static void fields2Map(Object bean, Field[] fields, Map<String, Object> map, boolean ignore) {

        try {

            int i = 0;

            do {
                fields[i].setAccessible(true);

                String name = fields[i].getName();

                Object value = fields[i].get(bean);

                if ((isNotNull(value)) || (!ignore))
                    map.put(name, value);

                i++;
                if (fields == null) break;
            } while (i < fields.length);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }


    private static boolean isNotNull(Object value) {

        if (value == null) {

            return false;

        }

        if ((value instanceof String)) {

            return StringUtil.isNotBlank(new String[]{(String) value});

        }

        return true;

    }

}

