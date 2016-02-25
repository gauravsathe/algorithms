package ctci;

public class Node {
	private int data;
	private Node next;
	
	public Node(int d) {
		this(d,null);
	}
	
	public Node(int d, Node next) {
		this.data = d;
		this.next = null;
	}
	
	public int getData() {
		return this.data;
	}
	
	public void setData(int d) {
		this.data = d;
	}
	
	public void appendNode(int d) {
		Node tail = new Node(d);
		Node n = this;
		while(n.next != null) n = n.next;
		
		n.next = tail;
	}
	
	public Node getNext() {
		return this.next;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
}
