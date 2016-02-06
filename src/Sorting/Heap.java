package Sorting;

import Linked_List.Node_Main;

import java.util.ArrayList;
import java.io.*;

public class Heap implements Serializable {

	private int heapSize;
	private ArrayList<Node_Main> heap;
	private int heapType;
	
	public Heap(int type) {
		heapSize = 0;
		heap = new ArrayList<Node_Main>();
		setHeapType(type);
	}
	
	public Heap(Node_Main[] nodes, int type) {
		
		if(nodes.length > 0) {
			heap = new ArrayList<Node_Main>();
			if(type == 1) {
				buildMaxHeap(nodes);
			}
			else {
				buildMinHeap(nodes);
			}
			setHeapType(type);
		}
	}
	
	public int getHeapType() {
		return heapType;
	}

	private void setHeapType(int heapType) {
		this.heapType = heapType;
	}
	
	public int getHeapSize() {
		return heapSize;
	}
	
	public int getIndex(Node_Main node) {
		return heap.indexOf(node);
	}
	
	public int parentNode(int index) {
		if( index == 0) return -1;
		
		return (int)Math.floor((index-1)/2);
	}
	
	public int leftChild(int index) {
		return (2*index+1);
	}
	
	public int rightChild(int index) {
		return (2*index+2);
	}
	
	public void maxHeapify(int index) {
		if(index >= heapSize) {
			return;
		}
		
		int largest = index;
		
		int left = leftChild(index);
		int right = rightChild(index);
		
		if(left < heapSize) {
			largest = heap.get(index).getValue() > heap.get(left).getValue() ? index : left; 
		}
		
		if(right < heapSize) {
			largest = heap.get(largest).getValue() > heap.get(right).getValue() ? largest : right;
		}
		
		if( largest != index ) {
			swapNodes(index, largest);
			maxHeapify(largest);
		}
	}
	
	public void minHeapify(int index) {
		if(index >= heapSize) {
			return;
		}
		
		int smallest = index;
		
		int left = leftChild(index);
		int right = rightChild(index);
		
		if(left < heapSize) {
			smallest = heap.get(index).getValue() < heap.get(left).getValue() ? index : left; 
		}
		
		if(right < heapSize) {
			smallest = heap.get(smallest).getValue() < heap.get(right).getValue() ? smallest : right;
		}
		
		if( smallest != index ) {
			swapNodes(index, smallest);
			minHeapify(smallest);
		}
	}
	
	public void buildMaxHeap(Node_Main[] nodes) {
		
		heapSize = nodes.length;
		
		for(int i=0; i<nodes.length; i++) {
			heap.add(nodes[i]);
		}
		
		for(int i=(int)Math.floor(heapSize/2) - 1; i>=0; i--) {
			maxHeapify(i);
		}
	}
	
	public void buildMinHeap(Node_Main[] nodes) {
		
		heapSize = nodes.length;
		
		for(int i=0; i<nodes.length; i++) {
			heap.add(nodes[i]);
		}
		
		for(int i=(int)Math.floor(heapSize/2); i>=0; i--) {
			minHeapify(i);
		}
	}
	
	public void heapSort() {
		int heapsize_old = heapSize;
		while(heapSize > 1) {
			swapNodes(0, heapSize-1);
			heapSize--;
			maxHeapify(0);
		}
		
		heapSize = heapsize_old;
	}
	
	public void insert(Node_Main newNode) {
		
		if(newNode != null) {
			int index = heapSize;
			int parent;
			
			heap.add(newNode);
			heapSize += 1;
			
			if( heapType == 1) {
				while( ((parent = parentNode(index)) != -1) && (heap.get(parent).getValue() < newNode.getValue()) ) {
					swapNodes(parent, index);
					index = parent;
				}
			}
			else {
				while( ((parent = parentNode(index)) != -1) && (heap.get(parent).getValue() > newNode.getValue()) ) {
					swapNodes(parent, index);
					index = parent;
				}
			}
			
		}
	}
	
	public void delete(Node_Main node) {
		int index = heap.indexOf(node);
		
		swapNodes(index, heapSize-1);
		heap.remove(heapSize-1);
		
		heapSize -= 1;
		
		if( heapType == 1) {
			maxHeapify(index);
		}
		else {
			minHeapify(index);
		}
	}
	
	public Node_Main extractMin() {
		
		if( heapType == 0 && heapSize > 0) {
			swapNodes(0, heapSize-1);
			Node_Main min = heap.remove(heapSize-1);
			heapSize -= 1;
			
			minHeapify(0);
			
			return min;
		}
		return null;
	}
	
	public Node_Main extractMax() {
		
		if( heapType == 1 && heapSize > 0) {
			swapNodes(0, heapSize-1);
			Node_Main max = heap.remove(heapSize-1);
			heapSize -= 1;
			
			maxHeapify(0);
			
			return max;
		}
		return null;
	}
	
	public int getNodeValue(int index) {
		if( index < heapSize ) {
			return heap.get(index).getValue();
		}
		else return 0;
	}
	
	public void increaseKey(int index, int newKey) {
		if(index < heapSize) {
			if(heap.get(index).getValue() > newKey) {
				System.out.println("New key smaller than current key");
				return;
			}
			
			heap.get(index).setValue(newKey);
			
			int parent;
			while( ((parent = parentNode(index)) != -1) && (heap.get(parent).getValue() < heap.get(index).getValue()) ) {
				swapNodes(parent, index);
				index = parent;
			}
		}
	}
	
	public void decreaseKey(int index, int newKey) {
		if(index < heapSize) {
			if(heap.get(index).getValue() < newKey) {
				System.out.println("New key greater than current key");
				return;
			}
			
			heap.get(index).setValue(newKey);
			
			int parent;
			while( ((parent = parentNode(index)) != -1) && (heap.get(parent).getValue() > heap.get(index).getValue()) ) {
				swapNodes(parent, index);
				index = parent;
			}
		}
	}
	
 	public void printHeap() {
		if(heapSize > 0) {
			for(int i=0; i<heapSize; i++) {
				System.out.print(heap.get(i).getValue() + " ");
			}	
		}
		System.out.println();
	}
	
	public void swapNodes(int i, int j) {
		Node_Main temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	
	public static void runningMedian(ArrayList<Integer> input) {
		Heap minHeap = new Heap(0);
		Heap maxHeap = new Heap(1);
		
		int medianSum = 0;
		int current_median = Integer.MAX_VALUE;
		
		for(int num : input) {
			Node_Main node = new Node_Main(num);
			
			if(num > current_median) {
				minHeap.insert(node);
			}
			else {
				maxHeap.insert(node);
			}
			
			if((maxHeap.getHeapSize() - minHeap.getHeapSize()) == 2) {
				Node_Main root = maxHeap.extractMax();
				minHeap.insert(root);
			}
			else if((minHeap.getHeapSize() - maxHeap.getHeapSize()) == 1) {
				Node_Main root = minHeap.extractMin();
				maxHeap.insert(root);
			}
			
			current_median = maxHeap.getNodeValue(0); 
			
			medianSum += current_median;
		}
		
		System.out.println(medianSum % 10000);
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> input = new ArrayList<Integer>();
		
		try {
			BufferedReader buff = new BufferedReader(new FileReader(new File("Median.txt")));
			
			String line;
			
			while( (line = buff.readLine()) != null) {
				input.add(Integer.parseInt(line));
			}
			
			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		runningMedian(input);
	}
}
