package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.MechanismMapper;
import com.kira.emercmdplat.pojo.Mechanism;
import com.kira.emercmdplat.service.MechanismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/8 17:51
 * @Description:
 */
@Service
public class MechanismServiceImpl implements MechanismService {

    @Autowired
    private MechanismMapper mm;

    @Override
    public int insert(Mechanism pojo) {
        return mm.insert(pojo);
    }

    @Override
    public boolean delete(Mechanism pojo) {
        return mm.delete(pojo);
    }

    @Override
    public boolean update(Mechanism pojo) {
        return mm.update(pojo);
    }

    @Override
    public Mechanism selectById(Integer id) {
        return mm.selectById(id);
    }

    @Override
    public List<Mechanism> queryForAll(Mechanism pojo) {
        return mm.queryForAll(pojo);
    }

    @Override
    public List<Mechanism> queryForPage(Mechanism pojo, Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public Long queryForCounts(Mechanism pojo) {
        return null;
    }
}
