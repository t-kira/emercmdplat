package com.kira.emercmdplat.mapper.base;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Location: HangZhou.China
 * @Date: 2020/2/3 0020 11:19
 * @Version: V1.0.0
 * @Description: 此类/接口(或继承它的接口) 对应的就是*Mapper.xml文件
 */
public interface BaseMapper<T>{
    /**
     * 增/插入数据
     * @param pojo
     * @return
     */
    int insert(T pojo);

    /**
     * 删（其实就是修改了状态，使其不显示，在客户端看来是删除了）
     * @param pojo
     * @return
     */
     boolean delete(T pojo);

    /**
     * 改，
     * @param pojo
     * @return
     */
     boolean update(T pojo);

    /**
     * 根据id查询单个
     * @param id
     * @return
     */
     T selectById(Integer id);

    /**
     * 按条件查询
     * @param pojo
     * @return
     */
    List<T> queryForAll(T pojo);
    List<T> queryForPage(Map<String, Object> map);
    Long queryForCounts(T pojo);

}
