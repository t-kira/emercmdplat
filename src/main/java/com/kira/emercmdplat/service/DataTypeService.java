package com.kira.emercmdplat.service;

import java.util.List;

import com.kira.emercmdplat.pojo.DataType;
import com.kira.emercmdplat.pojo.EType;
import com.kira.emercmdplat.service.base.BaseService;

public interface DataTypeService extends BaseService<DataType> {

	List<DataType> getResourcesByType(int id);

	List<DataType> getResourcesByJson(String json);

	List<EType> queryTypeListByDataId(Integer dataId);

}
