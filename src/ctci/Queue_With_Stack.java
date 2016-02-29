package ctci;

public class Queue_With_Stack {
	private Stack_DS main;
	private Stack_DS temp;
	
	public Queue_With_Stack() {
		this.main = new Stack_DS();
		this.temp = new Stack_DS();
	}
	
	public void EnQueue(int n) {
		this.main.Push(n);
	}
	
	public Node DeQueue() {
		if(this.main.isEmpty()) return null;
		
		while( !this.main.isEmpty() ) {
			Node n = this.main.Pop();
			this.temp.Push(n.getData());
		}
		
		Node t = this.temp.Pop();
		
		while( !this.temp.isEmpty() ) {
			Node n = this.temp.Pop();
			this.main.Push(n.getData());
		}
		
		return t;
	} 
}
