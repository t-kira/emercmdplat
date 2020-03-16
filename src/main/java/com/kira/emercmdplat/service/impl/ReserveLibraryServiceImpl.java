package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.ReserveLibraryMapper;
import com.kira.emercmdplat.pojo.ReserveLibrary;
import com.kira.emercmdplat.service.ReserveLibraryService;
import com.kira.emercmdplat.utils.PojoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/2/5 00:06
 * @Description:
 */
@Service
public class ReserveLibraryServiceImpl implements ReserveLibraryService {

    @Autowired
    private ReserveLibraryMapper reserveLibraryMapper;

    @Override
    public int insert(ReserveLibrary pojo) {
        return reserveLibraryMapper.insert(pojo);
    }

    @Override
    public boolean delete(ReserveLibrary pojo) {
        return reserveLibraryMapper.delete(pojo);
    }

    @Override
    public boolean update(ReserveLibrary pojo) {
        return reserveLibraryMapper.update(pojo);
    }

    @Override
    public ReserveLibrary selectById(Integer id) {
        return reserveLibraryMapper.selectById(id);
    }

    @Override
    public List<ReserveLibrary> queryForAll(ReserveLibrary pojo) {
        return reserveLibraryMapper.queryForAll(pojo);
    }

    @Override
    public List<ReserveLibrary> queryForPage(ReserveLibrary pojo, Integer page, Integer pageSize) {
        Map<String,Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return reserveLibraryMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(ReserveLibrary pojo) {
        return reserveLibraryMapper.queryForCounts(pojo);
    }
}
