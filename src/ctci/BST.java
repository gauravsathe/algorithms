package ctci;

import java.util.*;

public class BST {
	private BST_Node root;
	private int height;
	
	public BST() {
		this.root = null;
		this.height = 0;
	}
	
	public void setRoot(BST_Node r) {
		this.root = r;
	}
	
	public void treeHeight() {
		if(this.root != null) {
			this.height = this.root.nodeHeight();
		}
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public boolean balanced() {
		if(!this.root.isNodeBalanced()) return false;
		
		boolean leftBalanced = true, rightBalanced = true;
		
		if(this.root.getLeft() != null) {
			leftBalanced = this.root.getLeft().isNodeBalanced();
		}
		
		if(this.root.getRight() != null) {
			rightBalanced = this.root.getRight().isNodeBalanced();
		}
		
		return leftBalanced && rightBalanced;
	}
	
	// Run as : bst.setRoot(createBST(nodes, 0, nodes.length));
	public BST_Node createBST(int[] nodes, int start, int end) {
		if(start > end) {
			return null;
		}
		
		if(start == end) {
			return new BST_Node(nodes[start], null, null);
		}
		
		int mid = start + (end-start)/2 ;
		
		BST_Node n = new BST_Node(nodes[mid],null, null);
		
		n.setLeft(createBST(nodes, start, mid-1));
		n.setRight(createBST(nodes, mid+1, end));
		
		return n;
	}
	
	public BST_Node search(BST_Node target) {
		return this.search(this.root,target);
	}
	
	public BST_Node search(BST_Node root_curr, BST_Node target) {
		if(root_curr == null) return null;
		
		if(root_curr.getData() == target.getData()) {
			return root_curr;
		}
		
		if(root_curr.getData() < target.getData()) {
			return this.search(root_curr.getRight(), target);
		}
		else {
			return this.search(root_curr.getLeft(), target);
		}
	}
	
	public void Insert(BST_Node n) {
		if(this.root == null) {
			this.root = n;
		}
		else {
			this.Insert(this.root, n);
		}
		
		this.treeHeight();
	}
	
	public void Insert(BST_Node root_curr, BST_Node n) {
		
		if(n.getData() >= root_curr.getData()) {
			if(root_curr.getRight() == null) {
				root_curr.setRight(n);
			}
			else {
				this.Insert(root_curr.getRight(), n);
			}
		}
		else {
			if(root_curr.getLeft() == null) {
				root_curr.setLeft(n);
			}
			else {
				this.Insert(root_curr.getLeft(), n);
			}
		}
	}
	
	public void inOrder() {
		System.out.print("Inorder Traversal : ");
		this.inOrder(this.root);
		System.out.println();
	}
	
	public void inOrder(BST_Node r) {
		if(r == null) {
			return;
		}
		
		this.inOrder(r.getLeft());
		System.out.print(r.getData() + " ");
		this.inOrder(r.getRight());
	}
	
	public void levelOrderTraversal() {
		List<List<Integer>> levelTraversal = new ArrayList<List<Integer>>();
		for(int i=1; i<=this.height; i++) {
			List<Integer> curr = new ArrayList<Integer>();
			createLevelOrder(i, this.root, curr);
			
			levelTraversal.add(curr);
		}
		
		System.out.println("Level Order Traversal :");
		for(List<Integer> l : levelTraversal) {
			System.out.print("\t");
			for(Integer n : l) {
				System.out.print(n.intValue() + "-->");
			}
			System.out.println("null");
		}
		
	}
	
	public void createLevelOrder(int i, BST_Node root_curr, List<Integer> curr) {
		if(i == 1) {
			curr.add(new Integer(root_curr.getData()));
			return;
		}
		
		if(root_curr.getLeft() != null) createLevelOrder(i-1, root_curr.getLeft(), curr);
		if(root_curr.getRight() != null) createLevelOrder(i-1, root_curr.getRight(), curr);
	}
	
	public void isBST() {
		boolean bst = isBST(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.println("Is binary tree a binary search tree : " + bst);
	}
	
	public boolean isBST(BST_Node r, int lower, int upper) {
		if(lower <= r.getData() && r.getData() < upper) {
			boolean isBSTLeft = true, isBSTRight = true;
			
			if(r.getLeft() != null) isBSTLeft = isBST(r.getLeft(), lower, r.getData());
			
			if(r.getRight() != null) isBSTRight = isBST(r.getRight(), r.getData(), upper);
			
			return isBSTLeft && isBSTRight;
		}
		else return false;
	}
	
	public BST_Node minimum(BST_Node root_curr) {
		BST_Node r = root_curr;
		
		while(r.getLeft() != null) {
			r = r.getLeft();
		}
		
		return r;
	}
	
	public BST_Node maximum(BST_Node root_curr) {
		BST_Node r = root_curr;
		
		while(r.getRight() != null) {
			r = r.getRight();
		}
		
		return r;
	}
	
	public BST_Node successor(BST_Node n) {
		if(n.getRight() != null) {
			return minimum(n.getRight());
		}
		else {
			while(n.getParent() != null && n.getParent().getLeft() != n) {
				n = n.getParent();
			}
			
			if(n.getParent() == null) return n;
			else return n.getParent();
		}
	}
	
	public BST_Node lowestCommonAncestor(BST_Node n1, BST_Node n2) {
		return this.lowestCommonAncestor(this.root, n1, n2);
	}
	
	public BST_Node lowestCommonAncestor(BST_Node root_curr, BST_Node n1, BST_Node n2) {
		if(root_curr == n1 || root_curr == n2) {
			return root_curr;
		}
		
		boolean covers_n1 = this.search(root_curr.getLeft(), n1) != null;
		boolean covers_n2 = this.search(root_curr.getLeft(), n2) != null;
		
		if((covers_n1 && !covers_n2) || (!covers_n1 && covers_n2)) return root_curr;
		
		if(covers_n1 && covers_n2) return lowestCommonAncestor(root_curr.getLeft(), n1, n2);
		else return lowestCommonAncestor(root_curr.getRight(), n1, n2);
	}

	public void printPathsWithSum(int target) {
		int sum = 0;
		List<Integer> path = new ArrayList<Integer>();
		
		this.computePaths(this.root, target, sum, path);
	}
	
	public void computePaths(BST_Node root_curr, int target, int path_sum, List<Integer> path) {
		
		if(root_curr == null) {
			return;
		}
		
		
	}
}




