package Binary_Search_Tree;

import Linked_List.Node_Main;

public class BST_Demo {
	
	public static BST tree_demo;
	
	public static void main(String[] args) {
		tree_demo = new BST();
		
		tree_demo.insertNode(new Tree_Node(new Node_Main(15)));
		tree_demo.insertNode(new Tree_Node(new Node_Main(6)));
		tree_demo.insertNode(new Tree_Node(new Node_Main(18)));
		tree_demo.insertNode(new Tree_Node(new Node_Main(3)));
		tree_demo.insertNode(new Tree_Node(new Node_Main(7)));
		tree_demo.insertNode(new Tree_Node(new Node_Main(17)));
		tree_demo.insertNode(new Tree_Node(new Node_Main(20)));
		tree_demo.insertNode(new Tree_Node(new Node_Main(2)));
		tree_demo.insertNode(new Tree_Node(new Node_Main(4)));
		tree_demo.insertNode(new Tree_Node(new Node_Main(13)));
		tree_demo.insertNode(new Tree_Node(new Node_Main(9)));
		
		tree_demo.inOrderTraversal();
		System.out.println();
		
		System.out.println(tree_demo.OrderStatistic(7));
		
	
	}
	
	public static void search_demo(int v) {
		Tree_Node loc = tree_demo.search(8);
		printNode(loc);
	}
	
	public static void predecessor_demo(int v) {
		Tree_Node loc = tree_demo.predecessor(v);
		printNode(loc);
	}
	
	public static void printNode(Tree_Node loc) {
		try {
			System.out.print("Node with key " + loc.getObject().getValue());
			try {
				System.out.print(" has parent = " + loc.getParent().getObject().getValue() + ", ");
			} catch (NullPointerException e) {
				System.out.print(" has parent = null, ");
			}
			try {
				System.out.print("leftChild = " + loc.getLeft().getObject().getValue() + ", ");
			} catch (NullPointerException e) {
				System.out.print("leftChild = null, ");
			}
			try {
				System.out.println("rightChild = " + loc.getRight().getObject().getValue() + '.');
			} catch (NullPointerException e) {
				System.out.println("rightChild = null.");
			}
			
		} catch (NullPointerException n) {
			System.out.println("Node with given key not found in tree");
		}
	}
}
