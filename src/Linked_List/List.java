package Linked_List;

abstract public class List implements List_Methods {
	
	protected List_Node m_anchor;
	protected int m_length;
	
	public List() {
		m_anchor = new List_Node();
		m_length = 0;
	}
	
	public List(List_Node anc) {
		m_anchor = anc;
		m_length = 0;
	}
	
	public int getLength() { 
		return m_length; 
	}
	
	public Boolean isListEmpty() {
		if(m_length == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public List_Node getLastNode() {
		if( isListEmpty() == true ) {
			return null;
		}
		else {
			return (m_anchor).getPrev();
		}
	}
	
	public List_Node getFirstNode() {
		if( isListEmpty() == true ) {
			return null;
		}
		else {
			return (m_anchor).getNext();
		}
	}

	public List_Node getNextNode(List_Node baseNode) {
		List_Node nextNode = baseNode.getNext();
		if(nextNode == m_anchor) {
			return null;
		}
		else {
			return nextNode;
		}
	}
	
	public List_Node getPrevNode(List_Node baseNode) {
		List_Node prevNode = baseNode.getPrev();
		if(prevNode == m_anchor) {
			return null;
		}
		else {
			return prevNode;
		}
	}
		
	public List_Node findNode(Node_Main data) {
		if(isListEmpty()) {
			return null;
		}
		
		List_Node n = getFirstNode();
		while( n != null ) {
			if( data.getValue() == n.getObject().getValue() ) {
				return n;
			}
			n = getNextNode(n);
		}
		
		return null;
	}
	
	public void printList() {
		if(isListEmpty()) {
			System.out.println("List is empty");;
		}
		
		List_Node n = m_anchor;
		System.out.print(n.getObject().getValue()+"==>");
		
		n = getFirstNode();
		while( n != null ) {
			System.out.print(n.getObject().getValue()+"->");
			n = getNextNode(n);
		}
		
		System.out.println("NULL");
	}
}
