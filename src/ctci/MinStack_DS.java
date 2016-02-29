package ctci;

public class MinStack_DS extends Stack_DS {
	private Stack_DS minStack;
	
	public MinStack_DS() {
		super();
		this.minStack = new Stack_DS();
	}
	
	@Override
	public void Push(int d) {
		super.Push(d);
		if(this.minStack.getSize() > 0) {
			if(d <= this.minStack.peek()) {
				this.minStack.Push(d);
			}
		}
		else {
			this.minStack.Push(d);
		}
	}
	
	@Override
	public Node Pop() {
		Node t = super.Pop();
		if(t == null) {
			return null;
		}
		
		if(t.getData() == this.minStack.peek()) {
			this.minStack.Pop();
		}
		
		return t;
	}
	
	public int minElement() {
		return this.minStack.peek();
	}
}
