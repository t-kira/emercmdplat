package com.kira.emercmdplat.pojo;

public class PlanCatalog {

    private Integer id;
    /**
     * 节点
     */
    private String node;
    /**
     * 节点内容
     */
    private String nodeContent;
    /**
     * 父级ID
     */
    private Integer parentId;
    /**
     * 预案版本ID
     */
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
