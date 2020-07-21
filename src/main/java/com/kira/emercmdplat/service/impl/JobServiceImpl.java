package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.mapper.ContactMapper;
import com.kira.emercmdplat.mapper.JobMapper;
import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.Job;
import com.kira.emercmdplat.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/21 15:05
 * @Description:
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jm;
    @Autowired
    private ContactMapper cm;

    @Override
    public int insert(Job job) {
        return jm.insert(job);
    }

    @Override
    public boolean delete(Long id) {
        Contacts contacts = new Contacts();
        contacts.setjId(id);
        Long count = cm.queryForCounts(contacts);
        if (count > 0)
            throw new CustomException(ResultEnum.RELATED_DATA.getNo(), "该职位已被使用，不能删除");
        return jm.delete(id);
    }

    @Override
    public boolean update(Job job) {
        return jm.update(job);
    }

    @Override
    public Job selectById(Long id) {
        return jm.selectById(id);
    }

    @Override
    public List<Job> queryForAllOrPage(Job job) {
        if (job != null && job.getPage() != null) {
            job.setPage((job.getPage() - 1) * job.getPageSize());
        }
        return jm.queryForAllOrPage(job);
    }

    @Override
    public Long queryForCounts(Job job) {
        return jm.queryForCounts(job);
    }
}
