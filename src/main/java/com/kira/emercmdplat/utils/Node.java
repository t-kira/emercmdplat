package com.kira.emercmdplat.utils;

import java.util.ArrayList;
import java.util.List;

import com.terran4j.commons.api2doc.annotations.ApiComment;

import net.sf.json.JSONArray;

public class Node {
	
	@ApiComment(value = "id", sample = "1")
    private Integer id;
	
	@ApiComment(value = "节点名", sample = "aaa")
    private String name;
	
	@ApiComment(value = "父级ID", sample = "0")
    private Integer parentId;
	
	@ApiComment(value = "排序编号", sample = "1")
    private Integer order;
	
	@ApiComment(value = "子节点集合", sample = "[]")
    private List<Node> children;

    public Node(Integer id, String name, Integer parentId, Integer order) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public static List<Node> buildTree(List<Node> list, int parentId) {
        List<Node> nodes = new ArrayList<Node>();
        for (Node node : list) {
            int nodeId = node.getId();
            int pId = node.getParentId();
            if (parentId == pId) {
                List<Node> nodeLists = buildTree(list, nodeId);
                node.setChildren(nodeLists);
                nodes.add(node);
            }
        }
        return nodes;
    }

    public static void main(String[] args) {
        List<Node> list = new ArrayList<Node>();
        Node menu1 = new Node(1, "父级1", 0, 1);
        Node menu2 = new Node(2, "父级2", 0, 2);
        Node menu1_1 = new Node(3, "子级1_1", 1, 1);
        Node menu1_2 = new Node(4, "子级1_2", 1, 2);
        Node menu1_2_1 = new Node(5, "子级1_2_1", 4, 2);
        list.add(menu1);
        list.add(menu2);
        list.add(menu1_1);
        list.add(menu1_2);
        list.add(menu1_2_1);
        List<Node> listTree = buildTree(list, 0);
        String treeJson = JSONArray.fromObject(listTree).toString();
        System.out.println(treeJson);
    }

}
