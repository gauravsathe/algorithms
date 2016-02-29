package ctci;

public class Queue_DS {
	private Node head;
	private int length;
	
	public Queue_DS() {
		this.head = null;
		this.setLength(0);
	}
	
	public void Enqueue(int n) {
		this.head.appendNode(n);
		this.setLength(this.getLength() + 1);
	}
	
	public Node DeQueue() {
		if(this.head != null) {
			Node t = this.head;
			this.head = this.head.getNext();
			this.setLength(this.getLength() - 1);
			return t;
		}
		
		return null;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
