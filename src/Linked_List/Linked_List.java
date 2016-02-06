package Linked_List;

public class Linked_List extends List {
	
	public Linked_List() {
		super();
	}
	
	public Linked_List(List_Node anc) {
		super(anc);
	}
	
	public void appendNode(List_Node newNode) {
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
	
	public void prependNode(List_Node newNode) {
		if( isListEmpty() ) {
			newNode.setNext(m_anchor);
			newNode.setPrev(m_anchor);
			m_anchor.setNext(newNode);
			m_anchor.setPrev(newNode);
			m_length += 1;
			return;
		}
		
		List_Node firstNode = getFirstNode();
		
		newNode.setNext(firstNode);
		newNode.setPrev((List_Node)m_anchor);
		((List_Node)m_anchor).setNext(newNode);
		firstNode.setPrev(newNode);
		m_length += 1;
	}
	
	public void insertNodeBefore(List_Node baseNode, Node_Main data) {
		List_Node newNode = new List_Node(data);
		
		if( baseNode == null ) {
			prependNode(newNode);
			return;
		}
		
		List_Node prevNode = getPrevNode(baseNode);
		newNode.setNext(baseNode);
		newNode.setPrev(prevNode);
		
		prevNode.setNext(newNode);
		baseNode.setPrev(newNode);
		m_length += 1;
	}
	
	public void insertNodeAfter(List_Node baseNode, Node_Main data) {
		List_Node newNode = new List_Node(data);
		
		if( baseNode == null ) {
			appendNode(newNode);
			return;
		}
		
		List_Node nextNode = getNextNode(baseNode);
		newNode.setNext(nextNode);
		newNode.setPrev(baseNode);
		
		nextNode.setPrev(newNode);
		baseNode.setNext(newNode);
		m_length += 1;
	}
	
	public void unlinkNode(List_Node targetNode) {
		List_Node prevNode = targetNode.getPrev();
		List_Node nextNode = targetNode.getNext();
		
		prevNode.setNext(nextNode);
		nextNode.setPrev(prevNode);
		
		m_length -= 1;
	}
	
	public void unlinkAll() {
		List_Node targetNode;
		while(m_length != 0 ) {
			targetNode = getFirstNode();
			unlinkNode(targetNode);
		}
		
		m_anchor.setNext(null);
		m_anchor.setPrev(null);
	}
}
