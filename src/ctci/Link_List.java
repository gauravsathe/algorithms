package ctci;

import java.util.*;

public class Link_List {

	public static void main(String[] args) {
		Node head = new Node(3);
		head.appendNode(1);
		head.appendNode(5);
		head.appendNode(3);
		head.appendNode(7);
		head.appendNode(5);
		head.appendNode(1);

		printList(head);
		//head = removeDuplicates(head);
		//System.out.print("After removing duplicates : ");
		//printList(head);
		
		int k = 4;
		Node n = findKthToLastElement(head, k);
		if(n != null) System.out.println(String.format("%dth last element is %d", k, n.getData()));
		else System.out.println(String.format("List has less than %d nodes",k));
		
		Node partition = partitionList(head, 5);
		printList(partition);
		
		Node num1 = new Node(7);
		num1.appendNode(1);
		num1.appendNode(6);
		Node num2 = new Node(5);
		num2.appendNode(9);
		num2.appendNode(7);
		num2.appendNode(8);
		num2.appendNode(4);
		num2.appendNode(1);
		num2.appendNode(6);
		Node result = addTwoNumbers(num1, num2);
		System.out.print("List1 + List2 = ");
		printList(result);
		
		System.out.println();
		Node pal = new Node(4);
		pal.appendNode(5);
		pal.appendNode(6);
		pal.appendNode(8);
		pal.appendNode(6);
		pal.appendNode(5);
		pal.appendNode(7);
		System.out.println("Is list palindrome ? " + palindromeList(pal));
	}
	
	public static Node removeDuplicates(Node head) {
		if(head == null || head.getNext() == null) {
			return head;
		}
		
		/*
		// Using a temporary buffer
		HashMap<Integer,Boolean> hm = new HashMap<Integer,Boolean>();
		
		Node n = head;
		hm.put(n.getData(), true);
		while(n.getNext() != null) {
			Node next = n.getNext();
			if(hm.get(next.getData()) != null) {
				n.setNext(next.getNext());
			}
			else {
				hm.put(next.getData(), true);
				n = n.getNext();
			}
		}
		*/
		
		// Without using a temporary buffer
		Node curr = head.getNext();
		Node prev = head;
		while(curr != null) {
			Node h = head;
			while(h != curr && h.getData() != curr.getData()) {
				h = h.getNext();
			}
			
			if(h != curr) {
				prev.setNext(curr.getNext());
				curr = prev.getNext();
			}
			else {
				prev = curr;
				curr = curr.getNext();
			}
		}
		
		
		return head;
	}
	
	public static Node findKthToLastElement(Node head, int k) {
		if(head == null) {
			return null;
		}
		
		Node n1 = head, n2 = head;
		int i=1;
		
		while(i < k && n2 != null) {
			n2 = n2.getNext();
			i += 1;
		}
		
		if(n2 == null) {
			return null;
		}
		
		while(n2.getNext() != null) {
			n2 = n2.getNext();
			n1 = n1.getNext();
		}
		
		return n1;
	}
	
	public static void deleteMiddleNode(Node del) {
		if(del == null) {
			return;
		}
		
		while(del.getNext().getNext() != null) {
			del.setData(del.getNext().getData());
			del = del.getNext();
		}
		
		del.setData(del.getNext().getData());
		del.setNext(null);
	}
	
	public static Node partitionList(Node head, int pivot) {
		Node ptr_lt = null;
		Node n = head;
		Node prev = null;
		
		while(n != null) {
			if(n.getData() >= pivot) {
				prev = n;
				n = n.getNext();
			}
			else {
				if(ptr_lt == null) {
					ptr_lt = n;
					if(prev == null) {
						prev = n;
						n = n.getNext();
					}
					else {
						prev.setNext(n.getNext());
						ptr_lt.setNext(head);
						head = ptr_lt;
						n = prev.getNext();
					}
				}
				else {
					if(ptr_lt.getNext() == n) {
						ptr_lt = n;
						prev = n;
						n = n.getNext();
					}
					else {
						prev.setNext(n.getNext());
						n.setNext(ptr_lt.getNext());
						ptr_lt.setNext(n);
						ptr_lt = n;
						n = prev.getNext();
					}
				}
			}
		}
		
		return head;
	}
	
	public static Node addTwoNumbers(Node num1, Node num2) {
		if(num1 == null || num2 == null) {
			return null;
		}
		
		Node result = null;
		Node n = null;
		int d1, d2;
		int sum = 0, carry = 0;
		
		while(num1 != null && num2 != null) {
			d1 = num1.getData();
			d2 = num2.getData();
			
			sum = (d1 + d2 + carry) % 10;
			carry = (d1 + d2 + carry) / 10;
			
			if(n == null) {
				n = new Node(sum);
				result = n;
			}
			else {
				n.setNext(new Node(sum));
				n = n.getNext();
			}
			
			num1 = num1.getNext();
			num2 = num2.getNext();
		}
		
		if(num1 == null && num2 == null) {
			return result;
		}
		
		Node rem = (num1 == null) ? num2 : num1;
		if(carry == 0) {
			n.setNext(rem);
			return result;
		}
		
		while(carry != 0 && rem != null) {
			sum = (carry + rem.getData()) % 10;
			carry = (carry + rem.getData()) / 10;
			
			n.setNext(new Node(sum));
			n = n.getNext();
			rem = rem.getNext();
		}
		
		if(rem != null) {
			n.setNext(rem);
			return result;
		}
		else {
			if(carry == 0) return result;
			else {
				n.setNext(new Node(carry));
				return result;
			}
		}
	}
	
	public static boolean palindromeList(Node head) {
		if(head == null) return false;
		
		if(head.getNext() == null) {
			return true;
		}
		
		Node ptr1 = head, ptr2 = head;
		while(ptr2 != null && ptr2.getNext() != null) {
			ptr1 = ptr1.getNext();
			ptr2 = ptr2.getNext().getNext();
		}
		
		Node n = head;
		Node prev = null;
		Node temp = null;
		while(n != ptr1) {
			temp = n.getNext();
			n.setNext(prev);
			prev = n;
			n = temp;
		}
		
		Node h1 = prev;
		Node h2 = (ptr2 == null) ? ptr1 : ptr1.getNext();
		while(h1 != null && h2 != null) {
			if(h1.getData() != h2.getData()) {
				return false;
			}
			h1 = h1.getNext();
			h2 = h2.getNext();
		}
		
		return true;
	}
	
	public static void printList(Node head) {
		if(head == null) {
			return;
		}
		
		Node n = head;
		while(n != null) {
			System.out.print(n.getData() + "-->");
			n = n.getNext();
		}
		System.out.println("null");
	}

}
