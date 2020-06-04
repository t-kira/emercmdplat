package com.kira.emercmdplat.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class PlanVersionApproval {
	
	private Integer id;
	
	private Integer pvId;
	
	private Integer submitter;
	
	private Date createTime;
	
	private Integer examiner;
	
	private Date examineTime;
	
	private Integer status;
	
	private String opinion;
	
	private String submitterName;
	
	private String examinerName;

}
