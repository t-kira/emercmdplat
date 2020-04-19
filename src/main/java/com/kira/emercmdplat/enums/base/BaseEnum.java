package com.kira.emercmdplat.enums.base;

/**
 * @author kira
 * @version V1.0
 * @创建时间
 * @备注 枚举接口
 * @类名
 * @节点
 */
public interface BaseEnum<T> {

    /**
     * 获取属性int值
     *
     * @return
     */
    public Integer getNo();

    /**
     * 获取属性名称
     *
     * @return
     */
    public String getName();

    /**
     * 枚举值
     *
     * @param key
     * @return
     */
    public T getProperty(Integer key);

}
