package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.HazardSouce;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:07
 * @Description:危险源mapper
 */
public interface HazardSouceMapper extends BaseMapper<HazardSouce>{

    /**
     * 增/插入数据
     *
     * @param pojo
     * @return
     */
    @Override
    int insert(HazardSouce pojo);

    /**
     * 删（其实就是修改了状态，使其不显示，在客户端看来是删除了）
     *
     * @param pojo
     * @return
     */
    @Override
    boolean delete(HazardSouce pojo);

    /**
     * 改，
     *
     * @param pojo
     * @return
     */
    @Override
    boolean update(HazardSouce pojo);

    /**
     * 根据id查询单个
     *
     * @param id
     * @return
     */
    @Override
    HazardSouce selectById(Integer id);

    /**
     * 按条件查询
     *
     * @param pojo
     * @return
     */
    @Override
    List<HazardSouce> queryForAll(HazardSouce pojo);

    @Override
    List<HazardSouce> queryForPage(Map<String, Object> map);

    @Override
    Long queryForCounts(HazardSouce pojo);
}
