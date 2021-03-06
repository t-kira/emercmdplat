package com.kira.emercmdplat.pojo;

import java.util.List;

import com.terran4j.commons.api2doc.annotations.ApiComment;

import lombok.Data;

@Data
public class MedicalInstitutionResult {
	
	@ApiComment(value = "总条数", sample = "100")
	private long count;
	
	@ApiComment(value = "记录集合", sample = "[]", seeClass=MedicalInstitution.class)
	private List<MedicalInstitution> list;
	
}
