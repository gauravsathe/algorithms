package Linked_List;

public class Queue extends List {
	
	public void enqueue(List_Node newNode) {
		if( isListEmpty() ) {
			newNode.setNext(m_anchor);
			newNode.setPrev(m_anchor);
			m_anchor.setNext(newNode);
			m_anchor.setPrev(newNode);
			m_length += 1;
			return;
		}
		
		List_Node lastNode = getLastNode();
		
		newNode.setNext(m_anchor);
		newNode.setPrev(lastNode);
		m_anchor.setPrev(newNode);
		lastNode.setNext(newNode);
		m_length += 1;
	}
	
	public List_Node dequeueNode() {
		if(isListEmpty()) {
			return null;
		}
		
		List_Node head = getFirstNode();
		
		List_Node prevNode = head.getPrev();
		List_Node nextNode = head.getNext();
		
		prevNode.setNext(nextNode);
		nextNode.setPrev(prevNode);
		
		m_length -= 1;
		
		return head;
	}
	
}
