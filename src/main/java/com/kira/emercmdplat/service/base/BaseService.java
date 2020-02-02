package com.kira.emercmdplat.service.base;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/02/01 0018 21:35
 * @Version: V1.0.0
 */
public interface BaseService<T> {
    public boolean insert(T pojo);
    public boolean delete(T pojo);
    public boolean update(T pojo);
    public T selectById(Integer id);
    public List<T> queryForAll(T pojo);
    public List<T> queryForPage(T pojo, Integer page, Integer pageSize);
    public Long queryForCounts(T pojo);
}
