package com.jiongsoft.cocit.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class TreeNode {

	private String id;
	private TreeNode parent;
	private List<TreeNode> children;
	private Map<String, Object> props;

	public TreeNode() {
		children = new ArrayList<TreeNode>();
		props = new Hashtable();
	}

	public void addChild(TreeNode child) {
		children.add(child);
	}

	public void addProp(String fieldName, Object fieldValue) {
		props.put(fieldName, fieldValue);
	}

	public <T> T getProp(String fieldName) {
		return (T) props.get(fieldName);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

}
