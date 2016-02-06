package Binary_Search_Tree;

public interface Search_Tree {
	
	public void inOrderTraversal();
	public void preOrderTraversal();
	public void postOrderTraversal();
	public void insertNode(Tree_Node n);
	public Tree_Node search(int v);
}
