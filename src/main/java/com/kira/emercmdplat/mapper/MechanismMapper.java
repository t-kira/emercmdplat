package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.Mechanism;

import java.util.List;
/**
 * @Author: kira
 * @Date: 2020/4/8 17:48
 * @Description:
 */
public interface MechanismMapper {

    int insert(Mechanism mechanism);

    boolean delete(Long id);

    boolean update(Mechanism mechanism);

    Mechanism selectById(Long id);

    List<Mechanism> queryForAllOrPage(Mechanism mechanism);

    Long queryForCounts(Mechanism mechanism);
}
