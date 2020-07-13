package com.kira.emercmdplat.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.terran4j.commons.api2doc.annotations.ApiComment;

import lombok.Data;

/**
 * 预案审核
 * @author richard.yuq
 *
 */
@Data
public class PlanVersionApproval {
	
	@ApiComment(value = "id", sample = "1")
	private Integer id;
	
	@ApiComment(value = "预案id", sample = "1")
	@NotNull(message = "预案id不能为空")
	private Integer pvId;
	
	@ApiComment(value = "提交人", sample = "1")
	private Integer submitter;
	
	@ApiComment(value = "提交时间", sample = "1")
	private Date createTime;
	
	@ApiComment(value = "显示提交时间", sample = "1")
	private String sCreateTime;
	
	@ApiComment(value = "审核人", sample = "1")
	private Integer examiner;
	
	@ApiComment(value = "审核时间", sample = "1")
	private Date examineTime;
	
	@ApiComment(value = "显示审核时间", sample = "1")
	private String sExamineTime;
	
	@ApiComment(value = "审核状态 1通过 2未通过", sample = "1")
	private Integer status;
	
	@ApiComment(value = "审核意见", sample = "1")
	private String opinion;
	
	@ApiComment(value = "提交人姓名", sample = "1")
	private String submitterName;
	
	@ApiComment(value = "审核人姓名", sample = "1")
	private String examinerName;

}
