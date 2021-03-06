package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.MedicalInstitution;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:07
 * @Description:危险源mapper
 */
public interface MedicalInstitutionMapper extends BaseMapper<MedicalInstitution> {
	
	Long queryForCountsForUID(MedicalInstitution pojo);

}
