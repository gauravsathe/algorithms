package Linked_List;

public class List_Node {
	
	private Node_Main object;
	private List_Node m_next;
	private List_Node m_prev;
	
	public List_Node() {
		object = null;
		m_next = m_prev = this;
	}
	
	public List_Node(Node_Main o) {
		object = o;
		m_next = m_prev = this;
	}
	
	public List_Node(Node_Main o, List_Node next, List_Node prev) {
		object = o;
		m_next = next;
		m_prev = prev;
	}
	
	public Node_Main getObject() {
		return object;
	}

	public void setNext(List_Node next) {
		m_next = next;
	}
	
	public List_Node getNext() {
		return m_next;
	}
	
	public void setPrev(List_Node prev) {
		m_prev = prev;
	}
	
	public List_Node getPrev() { 
		return m_prev;
	}
	
}
