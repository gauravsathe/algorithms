package ctci;

import java.util.*;
import ctci.Node;

public class Stack_DS {
	private Node top;
	private int size;
	
	public Stack_DS() {
		this.top = null;
		this.setSize(0);
	}
	
	public void Push(int n) {
		Node temp = this.top;
		
		this.top = new Node(n);
		this.top.setNext(temp);
		
		this.setSize(this.getSize() + 1);
	}
	
	public Node Pop() {
		if(this.top != null) {
			Node t = this.top;
			this.top = this.top.getNext();
			this.setSize(this.getSize() - 1);
			return t;
		}
		
		return null;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int peek() {
		if(this.top != null) {
			return this.top.getData();
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return this.getSize() == 0;
	}
	
	public void sortStack() {
		if(this.isEmpty()) {
			return;
		}
		
		Stack_DS temp = new Stack_DS();
		
		while(!this.isEmpty()) {
			Node t = this.Pop();
			temp.Push(t.getData());
		}
		
		int s = temp.getSize();
		for(int i=1; i<=s; i++) {
			Node curr = temp.Pop();
			
			int count = 0;
			
			while(!this.isEmpty() && curr.getData() < this.peek()) {
				Node t = this.Pop();
				temp.Push(t.getData());
				count += 1;
			}
			
			this.Push(curr.getData());
			
			for(int j=1; j<=count; j++) {
				Node t = temp.Pop();
				this.Push(t.getData());
			}
		}
		Link_List.printList(this.top);
	}
	
}




