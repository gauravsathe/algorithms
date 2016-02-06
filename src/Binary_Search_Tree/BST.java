package Binary_Search_Tree;

public class BST implements Search_Tree {
	
	protected Tree_Node root;
	protected int height;
	protected int size;
	
	public BST() {
		root = null;
		setHeight(0);
		setSize(0);
	}
	
	public BST(Tree_Node r) {
		root = r;
		setHeight(1);
		setSize(1);
	}
	
	public Tree_Node getRoot() {
		return root;
	}
	public int getHeight() {
		return height;
	}

	protected void setHeight(int height) {
		this.height = height;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void inOrderTraversal() {
		
		/*
		Tree_Node oldRoot = this.root;
		if( oldRoot.getLeft() != null ) {
			this.root = oldRoot.getLeft();
			inOrderTraversal();
		}
		
		System.out.print(oldRoot.getObject().getValue() + "\t");
		
		if( oldRoot.getRight() != null) {
			this.root = oldRoot.getRight();
			inOrderTraversal();
		}
		
		this.root = oldRoot;*/
		inOrderTraversal(this.root);
	}
	
	public void inOrderTraversal(Tree_Node root) {
		if(root == null) {
			return;
		}
		
		inOrderTraversal(root.getLeft());
		System.out.println(root.getObject().getValue() + "\t");
		inOrderTraversal(root.getRight());
	}
	
	public void preOrderTraversal() {
		
		Tree_Node oldRoot = this.root;
		
		System.out.print(oldRoot.getObject().getValue() + "\t");
		
		if( oldRoot.getLeft() != null ) {
			this.root = oldRoot.getLeft();
			preOrderTraversal();
		}
		
		if(oldRoot.getRight() != null ) {
			this.root = oldRoot.getLeft();
			preOrderTraversal();
		}
		
		this.root = oldRoot;
	}
	
	public void postOrderTraversal() {
		
		Tree_Node oldRoot = this.root;
				
		if( oldRoot.getLeft() != null ) {
			this.root = oldRoot.getLeft();
			preOrderTraversal();
		}
		
		if(oldRoot.getRight() != null ) {
			this.root = oldRoot.getLeft();
			preOrderTraversal();
		}
		
		System.out.print(oldRoot.getObject().getValue() + "\t");
		
		this.root = oldRoot;
	}
	
	public void insertNode(Tree_Node newNode) {
		if(newNode == null) return;
		
		if(this.root == null) {
			this.root = newNode;
			this.root.setParent(null);
			setSize(getSize()+1);
			return;
		}
		
		Tree_Node oldRoot = this.root;
		
		if( oldRoot.getObject().getValue() > newNode.getObject().getValue() ) {
			if( oldRoot.getLeft() == null ) {
				oldRoot.setLeft(newNode);
				newNode.setParent(oldRoot);
				setSize(getSize()+1);
			}
			else {
				this.root = oldRoot.getLeft();
				insertNode(newNode);
			}
		}
		else if( oldRoot.getObject().getValue() <= newNode.getObject().getValue() ) {
			if( oldRoot.getRight() == null ) {
				oldRoot.setRight(newNode);
				newNode.setParent(oldRoot);
				setSize(getSize()+1);
			}
			else {
				this.root = oldRoot.getRight();
				insertNode(newNode);
			}
		}
		
		this.root = oldRoot;
		
		int newHeight;
		if( (newHeight = height(this.root)) > getHeight() ) {
			setHeight(newHeight);
		}
	}
	
	public Tree_Node search(int value) {
		
		if(this.root == null) return null;
		
		if(this.root.getObject().getValue() == value) {
			return this.root;
		}
		
		Tree_Node oldRoot = this.root;
		Tree_Node loc = null;
		
		if( oldRoot.getObject().getValue() > value ) {
			this.root = oldRoot.getLeft();
			loc = search(value);
		}
		else {
			this.root = oldRoot.getRight();
			loc = search(value);
		}
		
		this.root = oldRoot;
		
		return loc;
	}
	
	public Tree_Node maxNode(Tree_Node root) {
	
		while( root.getRight() != null) {
			root = root.getRight();
		}
		
		return root;
	}
	
	public Tree_Node minNode(Tree_Node root) {
		while( root.getLeft() != null) {
			root = root.getLeft();
		}
		return root;
	}
	
	public Tree_Node predecessor(int value) {
		
		Tree_Node loc = search(value);
		if( loc == null ) {
			//System.out.println("Node with key " + value + " not found in tree");
			return null;
		}
		
		if( loc.getLeft() != null ) {
			return maxNode(loc.getLeft());
		}
		else {
			while( (loc != this.root) && (loc != loc.getParent().getRight())  ) {
				loc = loc.getParent();
			}
			
			if(loc == this.root) {
				System.out.println("Node with key " + value + " is the minimum element in tree and has no predecessor");
				return null;
			}
			else {
				return loc.getParent();
			}
		}
	}
	
	public Tree_Node successor(int value) {
		
		Tree_Node loc = search(value);
		if( loc == null ) {
			System.out.println("Node with key " + value + " not found in tree");
			return null;
		}
		
		if( loc.getRight() != null ) {
			return minNode(loc.getRight());
		}
		else {
			while( (loc != this.root) && (loc != loc.getParent().getLeft()) ) {
				loc = loc.getParent();
			}
			
			if(loc == this.root) {
				System.out.println("Node with key " + value + " is the maximum element in tree and has no successor");
				return null;
			}
			else {
				return loc.getParent();
			}
		}
	}
	
	public int height(Tree_Node node) {
		/*
		 * The height of a node is the no. of edges from the node to the deepest leaf
		 */
		
		if( node == null ) {
			return 0;
		}
		
		int leftHeight = 0, rightHeight = 0;
		
		if(node.getLeft() != null) {
			leftHeight = height(node.getLeft()) + 1;
		}
		
		if( node.getRight() != null ) {
			rightHeight = height(node.getRight()) + 1;
		}
		
		return Math.max(leftHeight, rightHeight);
	}
	
	public int size(Tree_Node node) {
		
		if(node == null) {
			return 0;
		}
		
		int sizeLeft = size(node.getLeft());
		int sizeRight = size(node.getRight());
		
		return sizeLeft + sizeRight + 1;
	} 
	
	public int OrderStatistic(int i) {
		
		if( i > size(this.root) ) {
			System.out.println("i greater than size of tree");
			return -1;
		}
		
		Tree_Node oldRoot = this.root;
		
		int sizeLeft = size(oldRoot.getLeft());
		int stat;
		
		if( i == sizeLeft + 1) {
			return oldRoot.getObject().getValue();
		}
		else if( i <= sizeLeft ) {
			this.root = oldRoot.getLeft();
			stat = OrderStatistic(i);
		}
		else {
			this.root = oldRoot.getRight();
			stat = OrderStatistic(i-(sizeLeft+1));
		}
		
		this.root = oldRoot;
		return stat;
	}
}
