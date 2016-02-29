package ctci;

import java.util.*;

public class BST_Driver {

	public static void main(String[] args) {
		BST tree = new BST();
		
		tree.Insert(new BST_Node(6));
		tree.Insert(new BST_Node(5));
		tree.Insert(new BST_Node(7));
		tree.Insert(new BST_Node(2));
		tree.Insert(new BST_Node(5));
		tree.Insert(new BST_Node(8));
		
		tree.inOrder();
		
		tree.levelOrderTraversal();
		
		tree.isBST();
		
		
	}

}
