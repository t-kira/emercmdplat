package com.kira.emercmdplat.pojo;

import javax.validation.constraints.NotNull;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class PlanCatalog {

	@ApiComment(value = "id", sample = "1")
    private Integer id;
    /**
     * 节点
     */
	@ApiComment(value = "节点", sample = "aaa")
	@NotNull(message = "节点不能为空")
    private String node;
    /**
     * 节点内容
     */
	@ApiComment(value = "节点内容", sample = "aaa")
    private String nodeContent;
    /**
     * 父级ID
     */
	@ApiComment(value = "父级ID", sample = "1")
	@NotNull(message = "父级ID不能为空")
    private Integer parentId;
    /**
     * 预案ID
     */
	@ApiComment(value = "预案ID", sample = "1")
	@NotNull(message = "预案ID不能为空")
    private Integer pvId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getNodeContent() {
        return nodeContent;
    }

    public void setNodeContent(String nodeContent) {
        this.nodeContent = nodeContent;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPvId() {
        return pvId;
    }

    public void setPvId(Integer pvId) {
        this.pvId = pvId;
    }

}
