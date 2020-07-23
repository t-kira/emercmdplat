package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.Job;
import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/8 17:48
 * @Description:
 */
public interface JobMapper {

    int insert(Job job);

    boolean delete(Long id);

    boolean update(Job job);

    Job selectById(Long id);

    List<Job> queryForAllOrPage(Job job);

    Long queryForCounts(Job job);
}
