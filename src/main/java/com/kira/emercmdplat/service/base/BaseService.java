package com.kira.emercmdplat.service.base;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/02/01 0018 21:35
 * @Version: V1.0.0
 */
public interface BaseService<T> {
     int insert(T pojo);
     boolean delete(T pojo);
     boolean update(T pojo);
     T selectById(Integer id);
     List<T> queryForAll(T pojo);
     List<T> queryForPage(T pojo, Integer page, Integer pageSize);
     Long queryForCounts(T pojo);
}
