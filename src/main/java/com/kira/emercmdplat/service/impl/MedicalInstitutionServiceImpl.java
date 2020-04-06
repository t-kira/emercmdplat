package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.MedicalInstitutionMapper;
import com.kira.emercmdplat.pojo.MedicalInstitution;
import com.kira.emercmdplat.service.MedicalInstitutionService;
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
public class MedicalInstitutionServiceImpl implements MedicalInstitutionService {

    @Autowired
    private MedicalInstitutionMapper medicalInstitutionMapper;

    @Override
    public int insert(MedicalInstitution pojo) {
        return medicalInstitutionMapper.insert(pojo);
    }

    @Override
    public boolean delete(MedicalInstitution pojo) {
        return medicalInstitutionMapper.delete(pojo);
    }

    @Override
    public boolean update(MedicalInstitution pojo) {
        return medicalInstitutionMapper.update(pojo);
    }

    @Override
    public MedicalInstitution selectById(Integer id) {
        return medicalInstitutionMapper.selectById(id);
    }

    @Override
    public List<MedicalInstitution> queryForAll(MedicalInstitution pojo) {
        return medicalInstitutionMapper.queryForAll(pojo);
    }

    @Override
    public List<MedicalInstitution> queryForPage(MedicalInstitution pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return medicalInstitutionMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(MedicalInstitution pojo) {
        return medicalInstitutionMapper.queryForCounts(pojo);
    }
}
