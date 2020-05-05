package com.kira.emercmdplat.mapper;

import java.util.List;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.DataType;
import com.kira.emercmdplat.pojo.EType;

public interface DataTypeMapper extends BaseMapper<DataType> {

	List<EType> queryTypeListByDataId(Integer dataId);

}
