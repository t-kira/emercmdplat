package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.Point;
import com.kira.emercmdplat.pojo.PointExtend;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/26 11:04
 * @Description:
 */
public interface PointMapper {

    int insert(Point point);

    Point selectLastDataByResourceId(Long resourceId);

    List<Point> queryForAllOrPage(PointExtend pointExtend);

    Long queryForCounts(PointExtend pointExtend);

    void insertBatch(List<Point> list);
}
