package ctci;

public class BST_Node {
	private int data;
	private BST_Node left;
	private BST_Node right;
	private boolean balanced;
	
	public BST_Node(int data) {
		this(data,null,null);
	}
	
	public BST_Node(int data,BST_Node left, BST_Node right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public void setLeft(BST_Node new_left) {
		this.left = new_left;
	}
	
	public BST_Node getLeft() {
		return this.left;
	}
	
	public void setRight(BST_Node new_right) {
		this.right = new_right;
	}
	
	public BST_Node getRight() {
		return this.right;
	}
	
	public void setData(int new_data) {
		this.data = new_data;
	}
	
	public int getData() {
		return this.data;
	}
	
	public int nodeHeight() {
		if(this.left == null && this.right == null) {
			return 1;
		}
		
		int leftHeight = 0, rightHeight = 0;
		if(this.left != null) {
			leftHeight = this.left.nodeHeight();
		}
		
		if(this.right != null) {
			rightHeight = this.right.nodeHeight();
		}
		
		this.balanced = Math.abs(leftHeight - rightHeight) > 1;
		
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	public boolean isNodeBalanced() {
		return this.balanced;
	}

	public BST_Node getParent() {
		// TODO Auto-generated method stub
		return null;
	}
}
