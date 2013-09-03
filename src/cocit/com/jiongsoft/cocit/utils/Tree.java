package com.jiongsoft.cocit.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Tree {

	private Map<String, Node> nodeMap;

	private Map<String, Object> extProps;

	private List<Node> children;

	private Tree() {
		nodeMap = new HashMap();
		children = new ArrayList();
		extProps = new Hashtable();
	}

	public void optimizeStatus() {
		List<Node> list = getChildren();
		if (list.size() == 1) {
			Node node = list.get(0);
			List<Node> children = node.getChildren();
			this.children.remove(0);
			for (Node child : children) {
				this.children.add(child);
				child.setParent(null);
			}
		}
		for (Node child : children) {
			child.set("open", true);
		}
	}

	//
	// public void optimize() {
	// List<Node> list = getChildren();
	// // for (int i = list.size() - 1; i >= 0; i--) {
	// // removeEmptyFolder(list, i);
	// // }
	//
	// for (int i = list.size() - 1; i >= 0; i--) {
	// Node node = list.get(i);
	// List<Node> children = node.getChildren();
	// for (int j = children.size() - 1; j >= 0; j--) {
	// Node child = children.get(j);
	// move1ChildFolder(child);
	// }
	// }
	// }

	// 处理只有一个儿子的文件夹
	// private void move1ChildFolder(Node node) {
	// if ("folder".equals(node.getType())) {
	// if (node.getSize() == 1) {
	// Node child = node.getChildren().get(0);
	//
	// Node parent = node.getParent();
	// if (parent != null) {
	// // 将单个儿子节点往上移动
	// parent.getChildren().add(child);
	// child.setParent(parent);
	//
	// // 并从当前节点中移除
	// node.getChildren().remove(0);
	// parent.getChildren().remove(node);
	//
	// // 继续网上移动单个儿子节点
	// move1ChildFolder(child);
	// }
	// }
	// }
	// }

	//
	// // 移除空文件夹
	// private void removeEmptyFolder(List<Node> children, int index) {
	// Node node = children.get(index);
	// if (node.getParams() == null) {
	// if (node.getSize() != 0) {
	// List<Node> list2 = node.getChildren();
	// for (int i2 = list2.size() - 1; i2 >= 0; i2--) {
	// removeEmptyFolder(list2, i2);
	// }
	// }
	// if (node.getSize() == 0) {
	// children.remove(index);
	// }
	// }
	// }

	public <T> T getExtProp(String propName) {
		return (T) extProps.get(propName);
	}

	public Tree setExtProp(String propName, Object value) {
		extProps.put(propName, value);

		return this;
	}

	public static Tree make() {
		return new Tree();
	}

	public int count() {
		int total = this.getSize();
		for (Node node : children) {
			total += node.count();
		}

		return total;
	}

	public boolean existNode(String nodeID) {
		return nodeMap.get(nodeID) != null;
	}

	private Node getNode(String nodeID) {
		if (StringUtil.isNil(nodeID)) {
			return null;
		}

		Node node = nodeMap.get(nodeID);

		if (node == null) {
			node = new Node(nodeID);
			nodeMap.put(nodeID, node);
		}

		return node;
	}

	public Node addNode(String parentID, String nodeID) {
		return this.addNode(parentID, nodeID, -1);
	}

	public Node addNode(String parentID, String nodeID, int index) {
		Node p = getNode(parentID);
		Node n = getNode(nodeID);

		if (n != null) {
			if (p != null) {
				if (!p.getChildren().contains(n))
					p.addChild(n);
			} else if (!children.contains(n)) {
				if (index >= 0)
					children.add(index, n);
				else
					children.add(n);
			}
		}

		return n;
	}

	public Tree addChild(Node child) {
		child.parent = null;
		children.add(child);

		return this;
	}

	public int getSize() {
		if (children == null) {
			return 0;
		}
		return children.size();
	}

	public List<Node> getChildren() {
		return children;
	}

	public Iterator<Node> getAll() {
		return this.nodeMap.values().iterator();
	}

	/**
	 * <B>扩展属性：</B>
	 * <UL>
	 * <LI>open: BOOL值；
	 * <LI>checked: BOOL值；
	 * <LI>type: 字符串，可选值 folder | leaf；
	 * <LI>icon: 字符串，图片环境路径，如：/images/tree/folder.gif；
	 * </UL>
	 */
	public static final class Node {
		private List<Node> children = new ArrayList();

		private Node parent;

		private String id;// 节点ID

		private String name;// 节点名称

		private Integer sequence;// 节点顺序

		private Map<String, Object> extProps;

		private Node(String id) {
			this.id = id;
			this.extProps = new HashMap();
		}

		/**
		 * 获取节点扩展属性
		 * 
		 * @param propName
		 * @return
		 */
		public <T> T get(String propName) {
			return (T) extProps.get(propName);
		}

		/**
		 * 获取扩展属性的BOOL值
		 * 
		 * @param propName
		 * @return
		 */
		public boolean is(String propName) {
			Object obj = extProps.get(propName);
			if (obj == null)
				return false;

			try {
				return Boolean.valueOf(obj.toString());
			} catch (Throwable e) {
				return false;
			}
		}

		/**
		 * 设置节点扩展属性
		 * 
		 * @param propName
		 * @param value
		 * @return
		 */
		public Node set(String propName, Object value) {
			extProps.put(propName, value);

			return this;
		}

		public int count() {
			int total = this.size();
			for (Node node : children) {
				total += node.count();
			}

			return total;
		}

		public Node getParent() {
			return parent;
		}

		public List<Node> getChildren() {
			return children;
		}

		public Node addChild(Node child) {
			child.parent = this;
			children.add(child);

			return this;
		}

		public void removeChild(Node c) {
			children.remove(c);
			if (c.getParent() == this) {
				c.parent = null;
			}
		}

		public int size() {
			if (children == null) {
				return 0;
			}
			return children.size();
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public Node setName(String name) {
			this.name = name;

			return this;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public String toString() {
			return this.name;
		}

		@Override
		public int hashCode() {
			if (id == null) {
				return super.hashCode();
			}
			return 37 * 17 + id.hashCode();
		}

		@Override
		public boolean equals(Object that) {
			if (that == null)
				return false;

			if (!Node.class.isAssignableFrom(that.getClass())) {
				return false;
			}

			Node thatEntity = (Node) that;
			if (id == null || thatEntity.id == null) {
				return this == that;
			}

			return thatEntity.id.equals(id);
		}

		public Integer getSequence() {
			return sequence;
		}

		public void setSequence(Integer seq) {
			this.sequence = seq;
		}
	}

	public void sort() {
		sort(children);
	}

	private void sort(List<Node> list) {
		if (list == null) {
			return;
		}
		SortUtil.sort(list, "sequence", true);
		for (Node node : list) {
			sort(node.getChildren());
		}
	}

}
