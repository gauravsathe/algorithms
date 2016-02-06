package Binary_Search_Tree;

public class RedBlackTree extends BST implements Search_Tree {

	public RedBlackTree() {
		this.root = null;
		this.setHeight(0);
		setSize(0);
	}

	public RedBlackTree(Tree_Node r) {
		super(r);
	}
	
	public void insertNode(Tree_Node newNode) {
		if(this.root == null) {
			this.root = newNode;
			this.root.setColor("black");
			this.setSize(this.getSize() + 1);
			return;
		}
		
		Tree_Node r = this.root;
		Tree_Node parent = null;
		while(r != null) {
			if(newNode.getKey() > r.getKey()) {
				parent = r;
				r = r.getRight();
			}
			else {
				parent = r;
				r = r.getLeft();
			}
		}
		
		newNode.setParent(parent);
		if(parent.getKey() < newNode.getKey()) {
			parent.setRight(newNode);
		}
		else {
			parent.setLeft(newNode);
		}
		
		// Check if parent is root OR parent.color == black. Then we're done
		if(parent == this.root || parent.getColor().equals("black") == true) {
			return;
		}
		
		Tree_Node parent_parent = parent.getParent();
		Tree_Node sibling = (parent_parent.getRight() == parent) ? (parent_parent.getLeft()) : (parent_parent.getRight());

		// Case 1 : parent's sibling is "black" or "null". Then do left of right rotation
		if(sibling == null || sibling.getColor().equals("black") == true) {
			rotation(parent, parent_parent, sibling);
		}
		// Case 2 : parent's sibling is "red". Then swap colors and check for double red at parent
		else {
			parent_parent.setColor("red");
			parent.setColor("black");
			sibling.setColor("black");
			
			while(parent_parent.getParent() != null && parent_parent.getParent().getColor() != "black") {
				parent = parent_parent;
				parent_parent = parent.getParent();
				sibling = (parent_parent.getRight() == parent) ? (parent_parent.getLeft()) : (parent_parent.getRight());
				
				rotation(parent, parent_parent, sibling);
			}
			
			if(parent_parent.getParent() == null) {
				parent_parent.setColor("black");
			}
		}
		
	}
	

	public void rotation(Tree_Node parent, Tree_Node parent_parent, Tree_Node sibling) {
		if(sibling == parent_parent.getLeft()) {
			// Do left rotation
			Tree_Node parent_left = parent.getLeft();
			if(parent_parent == this.root) {
				this.root = parent;
				parent.setParent(null);
				this.root.setLeft(parent_parent);
				parent_parent.setParent(this.root);
			}
			else {
				if(parent_parent.getParent().getLeft() == parent_parent) {
					parent_parent.getParent().setLeft(parent);
				}
				else {
					parent_parent.getParent().setRight(parent);
				}
				parent.setParent(parent_parent.getParent());
				parent.setLeft(parent_parent);
				parent_parent.setParent(parent);
			}
			parent_parent.setRight(parent_left);
			parent_left.setParent(parent_parent);
			
			parent.setColor("black");
			parent_parent.setColor("red");
		}
		else {
			// Do right rotation
			Tree_Node parent_right = parent.getRight();
			if(parent_parent == this.root) {
				this.root = parent;
				parent.setParent(null);
				this.root.setRight(parent_parent);
				parent_parent.setParent(this.root);
			}
			else {
				if(parent_parent.getParent().getLeft() == parent_parent) {
					parent_parent.getParent().setLeft(parent);
				}
				else {
					parent_parent.getParent().setRight(parent);
				}
				parent.setParent(parent_parent.getParent());
				parent.setRight(parent_parent);
				parent_parent.setParent(parent);
			}
			parent_parent.setLeft(parent_right);
			parent_right.setParent(parent_parent);
			
			parent.setColor("black");
			parent_parent.setColor("red");
		}
	}
}
