package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.mapper.ContactMapper;
import com.kira.emercmdplat.mapper.MechanismMapper;
import com.kira.emercmdplat.pojo.Contacts;
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

    @Autowired
    private ContactMapper cm;

    @Override
    public int insert(Mechanism mechanism) {
        return mm.insert(mechanism);
    }

    @Override
    public boolean delete(Long id) {
        Contacts contacts = new Contacts();
        contacts.setmId(id);
        Long count = cm.queryForCounts(contacts);
        if (count > 0)
            throw new CustomException(ResultEnum.RELATED_DATA.getNo(), "该机构已被使用，不能删除");
        return mm.delete(id);
    }

    @Override
    public boolean update(Mechanism mechanism) {
        return mm.update(mechanism);
    }

    @Override
    public Mechanism selectById(Long id) {
        return mm.selectById(id);
    }

    @Override
    public List<Mechanism> queryForAllOrPage(Mechanism mechanism) {
        if (mechanism != null && mechanism.getPage() != null) {
            mechanism.setPage((mechanism.getPage() - 1) * mechanism.getPageSize());
        }
        return mm.queryForAllOrPage(mechanism);
    }

    @Override
    public Long queryForCounts(Mechanism mechanism) {
        return mm.queryForCounts(mechanism);
    }
}
