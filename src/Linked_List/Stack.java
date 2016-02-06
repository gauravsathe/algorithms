package Linked_List;

public class Stack extends List {
	
	public void push(List_Node top) {
		if( isStackEmpty() ) {
			top.setNext(m_anchor);
			top.setPrev(m_anchor);
			m_anchor.setNext(top);
			m_anchor.setPrev(top);
			m_length += 1;
			return;
		}
		
		List_Node currentTop = getFirstNode();
		
		currentTop.setPrev(top);
		top.setNext(currentTop);
		top.setPrev(m_anchor);
		m_anchor.setNext(top);
		m_length += 1;
	}
	
	public List_Node pop() {
		if( getFirstNode() == null ) {
			return null;
		}
		
		List_Node poppedNode = getFirstNode();
		
		List_Node nextNode = poppedNode.getNext();
		
		m_anchor.setNext(nextNode);
		nextNode.setPrev(m_anchor);
		
		m_length -= 1;
		
		return poppedNode;
	}
	
	public Boolean isStackEmpty() {
		return m_length == 0;
	}
}
