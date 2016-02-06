package Linked_List;

import Sorting.Heap;

abstract public class Priority_Queue {
	
	protected Heap pqueue;
	protected int queueLength;
	
	public Priority_Queue(int type) {
		pqueue = new Heap(type);
		queueLength = pqueue.getHeapSize();
	}
	
	public Priority_Queue(Node_Main[] nodes, int type) {
		pqueue = new Heap(nodes, type);
		queueLength = pqueue.getHeapSize();
	}
	
	public int getQueueLength() {
		return queueLength;
	}
	
	public void Insert(Node_Main newNode) {
		pqueue.insert(newNode);
		queueLength++;
	}
}
