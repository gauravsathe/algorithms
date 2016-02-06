package Linked_List;

public class Min_Priority_Queue extends Priority_Queue {
	
	public Min_Priority_Queue() {
		super(0);
	}
	
	public Min_Priority_Queue(Node_Main[] nodes) {
		super(nodes,0);
	}
	
	public int Minimum() {
		if(this.getQueueLength() > 0) {
			return pqueue.getNodeValue(0);
		}
		
		return -1;
	}
	
	public Node_Main Extract_Min() {
		Node_Main min = pqueue.extractMin();
		
		queueLength--;
		
		return min;
	}
	
	public void Decrease_Key(int index, int newKey) {
		pqueue.decreaseKey(index, newKey);
	}
	
	
}
