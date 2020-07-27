package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.Point;
import com.kira.emercmdplat.pojo.PointExtend;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/26 12:38
 * @Description:
 */
public interface PointService {

    Point selectLastDataByResourceId(Long resourceId);

    List<Point> queryForAllOrPage(PointExtend pointExtend);

    Long queryForCounts(PointExtend pointExtend);

}
