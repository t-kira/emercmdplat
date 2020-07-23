package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.Job;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/21 15:05
 * @Description:
 */
public interface JobService {

    int insert(Job job);

    boolean delete(Long id);

    boolean update(Job job);

    Job selectById(Long id);

    List<Job> queryForAllOrPage(Job job);

    Long queryForCounts(Job job);
}
