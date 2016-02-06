package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import Linked_List.Node_Main;
import Sorting.Heap;

public class Graph_2 {
	protected HashSet<Vertex> vertices;
	protected HashSet<Edge> edges;
	
	public Graph_2(int V) {
		vertices = new HashSet<Vertex>(V);
		edges = new HashSet<Edge>();
		
		for(int i=1; i<=V; i++) {
			vertices.add(new Vertex(i));
		}
	}
	
	public int getSize() {
		return vertices.size();
	}
	
	public Vertex getVertex(int v_num) {
		Iterator<Vertex> i = vertices.iterator();
		
		while(i.hasNext() == true) {
			Vertex next = i.next();
			if(next.getNumber() == v_num) {
				return next;
			}
		}
		
		return null;
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
		u.increaseDegree();
		
		v.addEdge(u);
		v.increaseDegree();
	}
	
	public void removeEdge(Edge e) {
		Vertex u = e.getVertex1();
		Vertex v = e.getVertex2();
		
		u.getadjList().remove(v);
		u.decreaseDegree();
		
		v.getadjList().remove(u);
		v.decreaseDegree();
		
		if( u.getDegree() == 0 ) {
			vertices.remove(u);
		}
		
		if( v.getDegree() == 0 ) {
			vertices.remove(v);
		}
	}	
	
	public void printGraph() {
		Iterator<Vertex> itr = vertices.iterator();
		
		while(itr.hasNext()) {
			itr.next().printList();
		}
	}
	
	public Vertex primsMinimumSpanningTree() {
		int src = (int)(Math.random() * vertices.size())+1;
		
		Vertex root = getVertex(src);
		root.setMSTKey(0);
		
		int i=0;
		
		Node_Main[] nodes = new Node_Main[vertices.size()];
		
		Iterator<Vertex> itr = vertices.iterator();
		while(itr.hasNext() == true) {
			nodes[i++] = itr.next();
		}
		
		Heap mstHeap = new Heap(nodes, 0);
		
		HashSet<Vertex> X = new HashSet<Vertex>();
		
		while(X.size() != vertices.size()) {
			Vertex mstNext = mstExtractMin(mstHeap, X);
			
			X.add(mstNext);
		}
		
		Iterator<Vertex> itr2 = vertices.iterator();
		while(itr2.hasNext() == true) {
			Vertex v = itr2.next();
			try {
				System.out.println(v.getNumber() + "(" +v.getMstPred().getNumber() +")");
			} catch (NullPointerException e) {
				System.out.println(v.getNumber() + "(null)");
			}
			
		}
				
		return root;
	}
	
	public Vertex mstExtractMin(Heap mstHeap, HashSet<Vertex> X) {
		Vertex next = (Vertex)(mstHeap.extractMin());
		
		Iterator<Edge> itr = edges.iterator();
		while(itr.hasNext() == true) {
			Edge e = itr.next();
			
			Vertex u = e.getVertex1();
			Vertex v = e.getVertex2();
			
			if(u.equals(next) == true) {
				if(X.contains(v) == false) {
					if(e.getEdgeWeight() < v.getMSTKey()) {
						v.setMSTKey(e.getEdgeWeight());
						v.setMstPred(u);
					}
					
					mstHeap.decreaseKey(mstHeap.getIndex(v), v.getMSTKey());
				}
			}
			else if(v.equals(next) == true) {
				if(X.contains(u) == false) {
					if(e.getEdgeWeight() < u.getMSTKey()) {
						u.setMSTKey(e.getEdgeWeight());
						u.setMstPred(v);
					}
					
					mstHeap.decreaseKey(mstHeap.getIndex(u), u.getMSTKey());
				}
			}
		}
		
		return next;
	}
	
	public HashSet<Edge> kruskalMinimumSpanningTree() {
		ArrayList<Edge> all_edges = new ArrayList<Edge>();
		
		Iterator<Edge> itr = edges.iterator();
		while(itr.hasNext() == true) {
			all_edges.add(itr.next());
		}
		
		Collections.sort(all_edges);
		
		HashSet<Edge> kruskal_mst = new HashSet<Edge>();
		
		return kruskal_mst;
		
	}
}
