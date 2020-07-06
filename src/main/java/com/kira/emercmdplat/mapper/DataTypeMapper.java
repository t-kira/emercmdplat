package com.kira.emercmdplat.mapper;

import java.util.List;
import java.util.Map;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.DataType;
import com.kira.emercmdplat.pojo.EType;
import org.apache.ibatis.annotations.Param;

public interface DataTypeMapper extends BaseMapper<DataType> {

	List<EType> queryTypeListByDataId(Integer dataId);

	List<DataType> queryResources(Map param);

	boolean truncateTable(@Param("tableName") String tableName);
}
