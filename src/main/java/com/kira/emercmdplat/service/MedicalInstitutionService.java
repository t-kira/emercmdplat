package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.MedicalInstitution;
import com.kira.emercmdplat.service.base.BaseService;

/**
 * @Author: kira
 * @Date: 2020/2/5 00:05
 * @Description:
 */
public interface MedicalInstitutionService extends BaseService<MedicalInstitution> {
	
	Long queryForCountsForUID(MedicalInstitution pojo);
	
}
