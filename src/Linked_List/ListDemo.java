package Linked_List;

public class ListDemo {

	public static void main(String[] args) {
		Queue stk = new Queue();
		
		List_Node[] nodes = new List_Node[5];
		nodes[0] = new List_Node(new Node_Main(6));
		nodes[1] = new List_Node(new Node_Main(7));
		nodes[2] = new List_Node(new Node_Main(3));
		nodes[3] = new List_Node(new Node_Main(2));
		nodes[4] = new List_Node(new Node_Main(9));
		
		stk.enqueue(nodes[0]);
		stk.enqueue(nodes[3]);
		stk.enqueue(nodes[2]);
		
		stk.printList();
		stk.dequeueNode();
		stk.printList();
		stk.enqueue(nodes[4]);
		stk.enqueue(nodes[1]);
		stk.dequeueNode();
		stk.printList();
	}

}
