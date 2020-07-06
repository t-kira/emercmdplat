package com.kira.emercmdplat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kira.emercmdplat.mapper.ContactMapper;
import com.kira.emercmdplat.mapper.DataTypeMapper;
import com.kira.emercmdplat.mapper.EmergencyTeamMapper;
import com.kira.emercmdplat.mapper.MedicalInstitutionMapper;
import com.kira.emercmdplat.mapper.PlanGroupMapper;
import com.kira.emercmdplat.mapper.PlanOrgMapper;
import com.kira.emercmdplat.mapper.ReserveLibraryMapper;
import com.kira.emercmdplat.mapper.ShelterMapper;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.pojo.DataType;
import com.kira.emercmdplat.pojo.EType;
import com.kira.emercmdplat.pojo.EmergencyTeam;
import com.kira.emercmdplat.pojo.MedicalInstitution;
import com.kira.emercmdplat.pojo.PlanGroup;
import com.kira.emercmdplat.pojo.PlanOrg;
import com.kira.emercmdplat.pojo.ReserveLibrary;
import com.kira.emercmdplat.pojo.Shelter;
import com.kira.emercmdplat.service.DataTypeService;
import com.kira.emercmdplat.utils.PojoUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author richard.yuq
 *
 */
@Service
public class DataTypeServiceImpl implements DataTypeService {

	@Autowired
    private DataTypeMapper dm;

	@Autowired
    private EmergencyTeamMapper etm;

	@Autowired
    private MedicalInstitutionMapper mim;

	@Autowired
	private ShelterMapper sm;

	@Autowired
	private ReserveLibraryMapper rlm;

	@Autowired
	private ContactMapper cm;

	@Autowired
	private PlanOrgMapper pm;

	@Autowired
    private PlanGroupMapper pgm;

	@Override
	public int insert(DataType pojo) {
		return dm.insert(pojo);
	}

	@Override
	public boolean delete(DataType pojo) {
		return dm.delete(pojo);
	}

	@Override
	public boolean update(DataType pojo) {
		return dm.update(pojo);
	}

	@Override
	public DataType selectById(Integer id) {
		return dm.selectById(id);
	}

	@Override
	public List<DataType> queryForAll(DataType pojo) {
		return dm.queryForAll(pojo);
	}

	@Override
	public List<DataType> queryForPage(DataType pojo, Integer page, Integer pageSize) {
		Map<String, Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return dm.queryForPage(paramMap);
	}

	@Override
	public Long queryForCounts(DataType pojo) {
		return dm.queryForCounts(pojo);
	}

	@Override
	public List<DataType> getResourcesByType(int id) {
		List<DataType> result = new ArrayList<>();
		if (id == 5) {
			List<EmergencyTeam> list = etm.queryForAll(null);
			for (EmergencyTeam et : list) {
				DataType d = new DataType();
				d.setId(et.getId());
				d.setName(et.getName());
				result.add(d);
			}
		} else if (id == 7) {
			List<MedicalInstitution> list = mim.queryForAll(null);
			for (MedicalInstitution mi : list) {
				DataType d = new DataType();
				d.setId(mi.getId());
				d.setName(mi.getName());
				result.add(d);
			}
		} else if (id == 9) {
			List<Shelter> list = sm.queryForAll(null);
			for (Shelter s : list) {
				DataType d = new DataType();
				d.setId(s.getId());
				d.setName(s.getName());
				result.add(d);
			}
		} else if (id == 13) {
			List<ReserveLibrary> list = rlm.queryForAll(null);
			for (ReserveLibrary rl : list) {
				DataType d = new DataType();
				d.setId(rl.getId());
				d.setName(rl.getName());
				result.add(d);
			}
		} else {

		}
		return result;
	}

	@Override
	public List<DataType> getResourcesByJson(String json) {
		JSONArray arr = JSONArray.fromObject(json);
		List<DataType> result = new ArrayList<>();
		for (Object obj : arr) {
			JSONObject js = (JSONObject) obj;
			int type = js.getInt("type");
			int id = js.getInt("id");
			DataType d = new DataType();
			d.setType(type);
			d.setTypeName(dm.selectById(type).getName());
			if (type == 5) {
				EmergencyTeam et = etm.selectById(id);
				d.setId(et.getId());
				d.setName(et.getName());
			} else if (type == 7) {
				MedicalInstitution mi = mim.selectById(id);
				d.setId(mi.getId());
				d.setName(mi.getName());
			} else if (type == 9) {
				Shelter s = sm.selectById(id);
				d.setId(s.getId());
				d.setName(s.getName());
			} else if (type == 13) {
				ReserveLibrary rl = rlm.selectById(id);
				d.setId(rl.getId());
				d.setName(rl.getName());
			} else {

			}
			result.add(d);
		}
		return result;
	}

	@Override
	public List<EType> queryTypeListByDataId(Integer dataId) {
		return dm.queryTypeListByDataId(dataId);
	}

	@Override
	public List<DataType> queryResources(DataType dataType) {
		Map param = new HashMap();
		param.put("dId", dataType.getId());
		param.put("name", dataType.getName());
		return dm.queryResources(param);
	}

	@Override
	public List<DataType> getPlanGroupMembers(String json) {
		JSONArray arr = JSONArray.fromObject(json);
		List<DataType> result = new ArrayList<>();
		for (Object obj : arr) {
			JSONObject js = (JSONObject) obj;
			int type = js.getInt("type");
			long id = js.getLong("id");
			DataType d = new DataType();
			d.setType(type);
			d.setTypeName(type==0?"通讯录":"指挥架构");
			if (type == 0) {//通讯录
				ContactsResult cr = cm.selectById(id);
				d.setId(cr.getId());
				d.setName(cr.getContactName());
			} else {//指挥架构
				PlanOrg org = pm.selectById(new Long(id).intValue());
				d.setId(new Long(org.getId()));
				d.setName(org.getName());
			}
			result.add(d);
		}
		return result;
	}

	@Override
	public List<DataType> getFlowTaskMembers(String json) {
		JSONArray arr = JSONArray.fromObject(json);
		List<DataType> result = new ArrayList<>();
		for (Object obj : arr) {
			JSONObject js = (JSONObject) obj;
			int type = js.getInt("type");
			long id = js.getLong("id");
			DataType d = new DataType();
			d.setType(type);
			if (type == 0) {//通讯录
				d.setTypeName("通讯录");
				ContactsResult cr = cm.selectById(id);
				d.setId(cr.getId());
				d.setName(cr.getContactName());
				d.setContent("");//职责
			} else if (type == 1) {//指挥架构
				d.setTypeName("指挥架构");
				PlanOrg org = pm.selectById(new Long(id).intValue());
				d.setId(new Long(org.getId()));
				d.setName(org.getName());
				d.setContent(org.getDuty());
			} else { //预案组
				d.setTypeName("预案组");
				PlanGroup group = pgm.selectById(new Long(id).intValue());
				d.setId(new Long(group.getId()));
				d.setName(group.getName());
				d.setContent(group.getDuty());
			}
			result.add(d);
		}
		return result;
	}

	@Override
	public boolean truncateTable(List<String> tableList) {
		for (String tableName : tableList) {
			dm.truncateTable(tableName);
		}
		return true;
	}
}
