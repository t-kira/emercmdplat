package com.kira.emercmdplat.service;

import java.util.List;

import com.kira.emercmdplat.pojo.DataType;
import com.kira.emercmdplat.pojo.EType;
import com.kira.emercmdplat.service.base.BaseService;

public interface DataTypeService extends BaseService<DataType> {

	List<DataType> getResourcesByType(int id);

	List<DataType> getResourcesByJson(String json);

	List<EType> queryTypeListByDataId(Integer dataId);

	List<DataType> queryResources(DataType dataType);

	List<DataType> getPlanGroupMembers(String json);

	List<DataType> getFlowTaskMembers(String json);

	boolean truncateTable(List<String> tableList);
}
