package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.PointMapper;
import com.kira.emercmdplat.pojo.Point;
import com.kira.emercmdplat.pojo.PointExtend;
import com.kira.emercmdplat.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/26 12:39
 * @Description:
 */
@Service
public class PointServiceImpl implements PointService {
    @Autowired
    private PointMapper pm;

    @Override
    public Point selectLastDataByResourceId(Long resourceId) {
        return pm.selectLastDataByResourceId(resourceId);
    }

    @Override
    public List<Point> queryForAllOrPage(PointExtend pointExtend) {
        if (pointExtend != null && pointExtend.getPage() != null) {
            pointExtend.setPage((pointExtend.getPage() - 1) * pointExtend.getPageSize());
        }
        return pm.queryForAllOrPage(pointExtend);
    }

    @Override
    public Long queryForCounts(PointExtend pointExtend) {
        return pm.queryForCounts(pointExtend);
    }
}
