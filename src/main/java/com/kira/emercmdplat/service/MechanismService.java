package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.Mechanism;
import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/8 17:50
 * @Description:机构service
 */
public interface MechanismService {

    int insert(Mechanism mechanism);

    boolean delete(Long id);

    boolean update(Mechanism mechanism);

    Mechanism selectById(Long id);

    List<Mechanism> queryForAllOrPage(Mechanism mechanism);

    Long queryForCounts(Mechanism mechanism);
}
