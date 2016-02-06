package Linked_List;

public class Max_Priority_Queue extends Priority_Queue {
	
	public Max_Priority_Queue() {
		super(1);
	}
	
	public Max_Priority_Queue(Node_Main[] nodes) {
		super(nodes,1);
	}
	
	public int Maximum() {
		if(this.getQueueLength() > 0) {
			return pqueue.getNodeValue(0);
		}
		
		return -1;
	}
	
	public Node_Main Extract_Max() {
		Node_Main max = pqueue.extractMax();
		
		queueLength--;
		
		return max;
	}
	
	public void Increase_Key(int index, int newKey) {
		pqueue.increaseKey(index, newKey);
	}
}
