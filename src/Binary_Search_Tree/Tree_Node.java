package Binary_Search_Tree;

import Linked_List.Node_Main;

public class Tree_Node {
	
	private Node_Main key;
	private Tree_Node leftChild, rightChild, parent;
	private String color;
	
	public Tree_Node() {
		this(null, null, null, null, "red");
	}

	public Tree_Node(Node_Main o) {
		this(o, null, null, null, "red");
	}

	public Tree_Node(Node_Main o, Tree_Node left, Tree_Node right) {
		this(o, left, right, null, "red");
	}
	
	public Tree_Node(Node_Main o, Tree_Node left, Tree_Node right, Tree_Node par, String color) {
		key = o;
		leftChild = left;
		rightChild = right;
		parent = par;
		this.color = color;
	}
	
	public Node_Main getObject() {
		return key;
	}
	
	public int getKey() {
		return getObject().getValue();
	}
	
	public void setLeft(Tree_Node left) {
		this.leftChild = left;
	}
	
	public Tree_Node getLeft() {
		return leftChild;
	}
	
	public void setRight(Tree_Node right) {
		this.rightChild = right;
	}
	
	public Tree_Node getRight() { 
		return rightChild;
	}

	public Tree_Node getParent() {
		return parent;
	}

	public void setParent(Tree_Node parent) {
		this.parent = parent;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
