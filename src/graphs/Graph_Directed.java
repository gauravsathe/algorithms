package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import Linked_List.List_Node;
import Linked_List.Stack;

public class Graph_Directed extends Graph {

	public Graph_Directed(int v) {
		super(v);
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		
		addEdge(v1, v2, 1);
	}
	
	public void addEdge(Vertex v1, Vertex v2, int edgeWeight) {
		
		v1.addEdge(v2);
		
		Edge newEdge = new Edge(v1, v2, edgeWeight);
		edges.add(newEdge);
		
	}
	
	public void removeEdge(Edge e) {
		Vertex v1 = e.getVertex1();
		Vertex v2 = e.getVertex2();
		
		v1.getadjList().remove(v2);
		//v1.decreaseDegree();
		
	}
	
	public Graph_Directed reverseGraph() {
		Graph_Directed rev = new Graph_Directed(V);
		
		for(Vertex x : vertices) {
			for(Vertex y : x.getadjList()) {
				rev.addEdge(rev.getvertex(y.getNumber()), rev.getvertex(x.getNumber()));
			}
		}
		
		return rev;
	}
	
	public void StronglyConnectedComponents() {
		Graph_Directed g_rev = reverseGraph();
		
		for(int i=V-1;i>=0;i--) {
			if( g_rev.getvertex(i).getColor().equals("white")) {
				g_rev.DepthFirstSearch(i);
			}
		}
		
		int[] finishing_times = new int[V];
		for(int i=0; i<V; i++) {
			finishing_times[g_rev.getvertex(i).getFinishTime()] = i;
		}
		
		ArrayList<Integer> scc_sizes = new ArrayList<Integer>();
		
		for(int i=V-1;i>=0;i--) {
			if( getvertex(finishing_times[i]).getColor().equals("white")) {
				int size = DepthFirstSearch(finishing_times[i]);
				scc_sizes.add(size);
			}
		}
	}
	
	public void checkCycle() {
		DepthFirstSearch(0);
	}
	
	public void computeLongestPaths(int src) {
		Stack dfs_stack = new Stack();
		
		Vertex source = getvertex(src);
		source.setColor("gray");
		source.setDFSPredecessor(null);
		dfs_stack.push(new List_Node(source));
		
		int[][] adjMatrix = new int[V][V];
		for(Edge e : edges) {
			adjMatrix[e.getVertex1().getNumber()][e.getVertex2().getNumber()] = e.getEdgeWeight();
			adjMatrix[e.getVertex2().getNumber()][e.getVertex1().getNumber()] = e.getEdgeWeight();
		}
		
		for(int i=0;i<V;i++) {
			for(int j=0; j<V;j++) {
				System.out.print(adjMatrix[i][j] + "\t");
			}
			System.out.println();
		}
		
		Vertex r;
		int got_leaf = 1;
		HashMap<Vertex, Integer> hM = new HashMap<Vertex, Integer>();
		
		hM.put(source, 0);
		for(int i=0; i<V; i++) {
			if( i != src ) hM.put(getvertex(i), Integer.MIN_VALUE);
		}
		
		while( !dfs_stack.isStackEmpty() ) {
			
			r = (Vertex)dfs_stack.getFirstNode().getObject();
			got_leaf = 1;
			
			for( Vertex v : r.getadjList()) {
				if( v.getColor().equals("white") ) {
					v.setDFSPredecessor(r);
					v.setColor("gray");
					dfs_stack.push(new List_Node(v));
					hM.put(v, hM.get(r) + adjMatrix[r.getNumber()][v.getNumber()]);
					
					got_leaf = 0;
				}
				else if( (hM.get(r) + adjMatrix[r.getNumber()][v.getNumber()]) > hM.get(v) ) {
					hM.put(v, hM.get(r) + adjMatrix[r.getNumber()][v.getNumber()]);
				}
			}
			if(got_leaf == 1) {
				r = (Vertex)dfs_stack.pop().getObject();
				r.setColor("black");
			}	
		}
		
		for(Vertex v : vertices) {
			System.out.println(v.getNumber() + ":" + hM.get(v));
		}
	}
}
