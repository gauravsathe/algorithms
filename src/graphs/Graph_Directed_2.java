package graphs;

import java.util.Iterator;

import Sorting.Heap;

import java.util.HashSet;

import Linked_List.Node_Main;

public class Graph_Directed_2 extends Graph_2 {
	
	public Graph_Directed_2(int V) {
		super(V);
	}
	
	public void addEdge(int v1, int v2) {
		
		addEdge(v1, v2, 1);
	}
	
	public void addEdge(int v1, int v2, int edgeWeight) {
		
		Vertex u = getVertex(v1);
		Vertex v = getVertex(v2);
		
		Edge newEdge = new Edge(u, v, edgeWeight);
		
		if( edges.add(newEdge) == false ) return;
		
		u.addEdge(v);
			
	}
	 
	public void DijkstraShortestPath(int src) {
		Vertex root = getVertex(src);
		
		root.setShortestPathDistance(0);
		
		Node_Main[] all_vertices = new Node_Main[vertices.size()];
		int i=0;
		
		Iterator<Vertex> itr = vertices.iterator();
		while(itr.hasNext() == true) {
			all_vertices[i++] = itr.next();
		}
		
		Heap dij_minHeap = new Heap(all_vertices, 0);
		
		HashSet<Vertex> vert_curr = new HashSet<Vertex>();
		
		while(vert_curr.size() != vertices.size()) {
			Vertex v_next = dijkstraExtractMin(dij_minHeap, vert_curr);
			
			vert_curr.add(v_next);
		}
		
		Iterator<Vertex> itr2 = vertices.iterator();
		while(itr2.hasNext() == true) {
			Vertex v = itr2.next();
			try {
				System.out.println(v.getNumber() + "(" +v.getShortestPathDistance() +")" + "(" +v.getPred().getNumber() +")");
			} catch (NullPointerException e) {
				System.out.println(v.getNumber() + "(" +v.getShortestPathDistance() +")" + "(null)");
			}	
		}
	}
	
	public Vertex dijkstraExtractMin(Heap dij_heap, HashSet<Vertex> X) {
		Vertex root = (Vertex)(dij_heap.extractMin());
		
		Iterator<Edge> itr = edges.iterator();
		while(itr.hasNext() == true) {
			Edge e = itr.next();
			
			Vertex u = e.getVertex1();
			Vertex v = e.getVertex2();
			
			if(u.equals(root) == true) {
				if( X.contains(v) == false ) {
					if(relaxEdge(u,v,e) == 1) {
						dij_heap.decreaseKey(dij_heap.getIndex(v), v.getShortestPathDistance());
					}
				}
			}
		}
		
		return root;
	}
	
	public int relaxEdge(Vertex u, Vertex v, Edge e) {
		if(v.getShortestPathDistance() > u.getShortestPathDistance() + e.getEdgeWeight()) {
			v.setShortestPathDistance(u.getShortestPathDistance()+e.getEdgeWeight());
			v.setPred(u);
			return 1;
		}
		return 0;
	}
}
