package Linked_List;

public interface List_Methods {
	int getLength();
	Boolean isListEmpty();
	List_Node getLastNode();
	List_Node getFirstNode();
	List_Node getNextNode(List_Node baseNode);
	List_Node getPrevNode(List_Node baseNode);
	List_Node findNode(Node_Main data);
	void printList();
}
