package com.kira.emercmdplat.pojo;

import java.util.Date;

import com.terran4j.commons.api2doc.annotations.ApiComment;

import lombok.Data;

@Data
public class PlanVersionApproval {
	
	@ApiComment(value = "id", sample = "1")
	private Integer id;
	
	@ApiComment(value = "预案id", sample = "1")
	private Integer pvId;
	
	@ApiComment(value = "提交人", sample = "1")
	private Integer submitter;
	
	@ApiComment(value = "提交时间", sample = "1")
	private Date createTime;
	
	@ApiComment(value = "审核人", sample = "1")
	private Integer examiner;
	
	@ApiComment(value = "审核时间", sample = "1")
	private Date examineTime;
	
	@ApiComment(value = "审核状态 1通过 2未通过", sample = "1")
	private Integer status;
	
	@ApiComment(value = "审核意见", sample = "1")
	private String opinion;
	
	@ApiComment(value = "提交人姓名", sample = "1")
	private String submitterName;
	
	@ApiComment(value = "审核人姓名", sample = "1")
	private String examinerName;

}