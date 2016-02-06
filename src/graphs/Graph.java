package graphs;

import java.util.ArrayList;

import Linked_List.Linked_List;
import Linked_List.List_Node;
import Linked_List.Node_Main;
import Linked_List.Queue;
import Linked_List.Stack;
import Sorting.Heap;

import java.lang.Math;
import java.util.Iterator;

public class Graph {
	
	protected int V;
	protected ArrayList<Vertex> vertices;
	protected ArrayList<Edge> edges;
	
	private static int t = 0;
	
 	public Graph(int v) {
		V = v;
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		
		for(int i=0; i<V; i++) {
			vertices.add(new Vertex(i));
		}
		
	}
	
 	public int getSize() {
 		return V;
 	}
 	
	public Vertex getvertex(int number) {
		return vertices.get(number);
	}
	
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
	
		addEdge(v1, v2, 1);
	}
	
	public void addEdge(Vertex v1, Vertex v2, int edgeWeight) {
		
		v1.addEdge(v2);
		v1.increaseDegree();
		
		v2.addEdge(v1);
		v2.increaseDegree();
	
		Edge newEdge = new Edge(v1, v2, edgeWeight);
		edges.add(newEdge);
	}
	
	public void removeEdge(Edge e) {
		Vertex v1 = e.getVertex1();
		Vertex v2 = e.getVertex2();
		
		v1.getadjList().remove(v2);
		v1.decreaseDegree();
		
		v2.getadjList().remove(v1);
		v2.decreaseDegree();
		
		if( v1.getDegree() == 0 ) {
			vertices.remove(v1);
		}
		
		if( v2.getDegree() == 0 ) {
			vertices.remove(v2);
		}
		
	}
	
	public void BreadthFirstSearch(int src) {
		
		Queue bfs_queue = new Queue();
		
		Vertex source = getvertex(src);
		
		source.setPred(null);
		source.setColor("gray");
		source.setShortestPathDistance(0);
		
		bfs_queue.enqueue(new List_Node(source));
		
		Vertex r;
		while( !bfs_queue.isListEmpty() ) {
			r = (Vertex)bfs_queue.dequeueNode().getObject();
			
			for(Vertex v : r.getadjList()) { 
				if( v.getColor().equals("white") ) {
					v.setColor("gray");
					v.setShortestPathDistance(r.getShortestPathDistance() + 1);
					v.setPred(r);
					bfs_queue.enqueue(new List_Node(v));
				}
			}
			r.setColor("black");
		}
		
		for(int i=0; i<V; i++) {
			try {
				System.out.println("Vertex "+i+" has bfs predecessor "+getvertex(i).getPred().getNumber()+" and shortest path distance = "+getvertex(i).getShortestPathDistance());
			} catch (NullPointerException n) {
				System.out.println("Vertex "+i+" has bfs predecessor null and shortest path distance 0");
			}
		}
	}
	
	public int DepthFirstSearch(int src) {
		
		Stack dfs_stack = new Stack();
		int dfs_tree_size = 0;
		
		Vertex source = getvertex(src);
		source.setColor("gray");
		source.setDFSPredecessor(null);
		dfs_stack.push(new List_Node(source));
		dfs_tree_size += 1;
		
		Vertex r;
		int got_leaf = 1;
		
		while( !dfs_stack.isStackEmpty() ) {
			
			r = (Vertex)dfs_stack.getFirstNode().getObject();
			got_leaf = 1;
			
			for( Vertex v : r.getadjList()) {
				if( v.getColor().equals("white") ) {
					v.setDFSPredecessor(r);
					v.setColor("gray");
					dfs_stack.push(new List_Node(v));
					dfs_tree_size += 1;
					
					got_leaf = 0;
				}
			}
			if(got_leaf == 1) {
				r = (Vertex)dfs_stack.pop().getObject();
				r.setColor("black");
				r.setFinishTime(t);
				
				t += 1;
			}	
		}
		return dfs_tree_size;
		/*
		for(int i=0; i<V; i++) {
			try {
				System.out.println("Vertex "+i+" has dfs predecessor "+getvertex(i).getDFSPredecessor().getNumber()+" and finishing time = "+getvertex(i).getFinishTime());
			} catch (NullPointerException n) {
				System.out.println("Vertex "+i+" has dfs predecessor null and finishing time = "+getvertex(i).getFinishTime());
			}
		}
		*/
	}

	public void printGraph() {
		for(int i=0; i<V; i++) {
			getvertex(i).printList();
		}
	}
	
	public int computeMinCut() {
		
		int randomEdge;
		
		while( vertices.size() > 2 ) {
			randomEdge = (int)(Math.random() * edges.size());
			
			Edge e = edges.get(randomEdge);
			
			Vertex v1 = e.getVertex1();
			Vertex v2 = e.getVertex2();
			
			int minV = Math.min(v1.getNumber(), v2.getNumber());
			
			Vertex k,r;
			if( v1.getNumber() == minV ) {
				r = v2;
				k = v1;
			}
			else {
				r = v1;
				k = v2;
			}
			
			for(Vertex v : r.getadjList()) {
				if( v != k) {
					addEdge(k, v);
				}
			}
			
			Iterator<Edge> iter = edges.iterator();
			while(iter.hasNext()) {
				Edge f = iter.next();
				if( f.getVertex1() == r || f.getVertex2() == r ) {
					removeEdge(f);
					iter.remove();
				}
			}
		}
		
		return edges.size();
	}
	
	public void DjikstraShortestPath(int src) {
		
		// Set initial distances
		getvertex(src).setShortestPathDistance(0);
		for(Vertex v : vertices) {
			v.setValue(v.getShortestPathDistance());
		}
		
		int[][] adjMatrix = new int[V][V];
		for(Edge e : edges) {
			adjMatrix[e.getVertex1().getNumber()][e.getVertex2().getNumber()] = e.getEdgeWeight();
			adjMatrix[e.getVertex2().getNumber()][e.getVertex1().getNumber()] = e.getEdgeWeight();
		}
		
		Heap minHeap = new Heap(vertices.toArray(new Node_Main[vertices.size()]), 0);
		
		while( minHeap.getHeapSize() != 0 ) {
			Vertex v = (Vertex)minHeap.extractMin();
			
			for( Vertex x : v.getadjList() ) {
				
				if( (v.getShortestPathDistance() + adjMatrix[v.getNumber()][x.getNumber()]) < x.getShortestPathDistance() ) {
					
					x.setShortestPathDistance(v.getShortestPathDistance() + adjMatrix[v.getNumber()][x.getNumber()]);
					x.setPred(v);
					minHeap.decreaseKey(minHeap.getIndex(x), x.getShortestPathDistance());
					
				}
			}
		}
		
		int[] output = {6,36,58,81,98,114,132,164,187,196};
		
		for(int i=0; i<output.length; i++) {
			System.out.println("Vertex "+output[i]+" has shortest path distance = "+getvertex(output[i]).getShortestPathDistance());
		}
		
		//for(Vertex y : vertices) {
		//	System.out.println("Vertex "+y.getNumber()+" has shortest path distance = "+y.getShortestPathDistance());
		//}
	}
	
	public void findPath(int src, int dest) {
		DepthFirstSearch(src);
		
		if(getvertex(dest).getColor().equals("black")) {
			Vertex v = getvertex(dest);
			while( v != getvertex(src) ) {
				System.out.print(v.getNumber() + "<--");
				v = v.getDFSPredecessor();
			}
			System.out.println(v.getNumber());
		}
		else {
			System.out.println("No path from vertex "+src+" to vertex " + dest);
		}
	}
	
	public Boolean checkIfGraphIsATree() {
		Stack dfs_stack = new Stack();
		
		Vertex source = getvertex(0);
		source.setColor("gray");
		source.setDFSPredecessor(null);
		dfs_stack.push(new List_Node(source));
		
		Vertex r;
		int got_leaf = 1;
		
		while( !dfs_stack.isStackEmpty() ) {
			
			r = (Vertex)dfs_stack.getFirstNode().getObject();
			got_leaf = 1;
			
			for( Vertex v : r.getadjList()) {
				if( v.getColor().equals("white") ) {
					v.setDFSPredecessor(r);
					v.setColor("gray");
					dfs_stack.push(new List_Node(v));
					
					got_leaf = 0;
				}
				else if(v.getColor().equals("gray") && v.getadjList().contains(r) == false ){
					return false;
				}
				else {
					return false;
				}
			}
			if(got_leaf == 1) {
				r = (Vertex)dfs_stack.pop().getObject();
				r.setColor("black");
			}	
		}
		
		for(Vertex v : vertices) {
			if(v.getColor().equals("white")) {
				return false;
			}
		}
		return true;
	}
}
