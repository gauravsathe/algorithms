package graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Graph_Demo {

	public static void main(String[] args) {
		/*
		Graph g1 = new Graph(8);
		
		g1.addEdge(g1.getvertex(0), g1.getvertex(1));
		g1.addEdge(g1.getvertex(0), g1.getvertex(4));
		g1.addEdge(g1.getvertex(1), g1.getvertex(5));
		g1.addEdge(g1.getvertex(2), g1.getvertex(3));
		g1.addEdge(g1.getvertex(2), g1.getvertex(5));
		g1.addEdge(g1.getvertex(2), g1.getvertex(6));
		g1.addEdge(g1.getvertex(3), g1.getvertex(6));
		g1.addEdge(g1.getvertex(3), g1.getvertex(7));
		g1.addEdge(g1.getvertex(5), g1.getvertex(6));
		g1.addEdge(g1.getvertex(6), g1.getvertex(7));
		
		//g1.printGraph();
		
		//g1.BreadthFirstSearch(1);
		//System.out.println();
		//g1.DepthFirstSearch(1);
		*/
		/*
		Graph_Directed g1 = new Graph_Directed(6);
		
		g1.addEdge(g1.getvertex(0), g1.getvertex(3),4);
		g1.addEdge(g1.getvertex(0), g1.getvertex(5),2);
		g1.addEdge(g1.getvertex(1), g1.getvertex(0),3);
		g1.addEdge(g1.getvertex(1), g1.getvertex(4),5);
		g1.addEdge(g1.getvertex(2), g1.getvertex(3),-1);
		g1.addEdge(g1.getvertex(2), g1.getvertex(5),1);
		g1.addEdge(g1.getvertex(3), g1.getvertex(5),-2);
		g1.addEdge(g1.getvertex(4), g1.getvertex(0),2);
		g1.addEdge(g1.getvertex(4), g1.getvertex(2),6);
		
		g1.computeLongestPaths(4);
		*/
		
		Graph g1 = new Graph(5);
		
		g1.addEdge(g1.getvertex(0), g1.getvertex(2));
		g1.addEdge(g1.getvertex(0), g1.getvertex(3));
		g1.addEdge(g1.getvertex(1), g1.getvertex(0));
		g1.addEdge(g1.getvertex(3), g1.getvertex(4));
		
		
		System.out.println(g1.checkIfGraphIsATree());
		
		// Coursera Week 3 programming problem
		/*
		ArrayList<Integer> allCuts = new ArrayList<Integer>();
		int crossingEdges;
		for(int j=0; j<(int)(1); j++) {
		
			Graph g2 = new Graph(200);
			try {
				File input = new File("kargerMinCut.txt");
				FileReader fr = new FileReader(input);
				BufferedReader buff = new BufferedReader(fr);
				
				String line;
				String[] adjList;
				
				ArrayList<Edge> edges_temp;
				while((line = buff.readLine()) != null) {
					adjList = line.split("\t");
					for(int i=1; i<adjList.length; i++) {
					
						int v1 = Integer.parseInt(adjList[0])-1;
						int v2 = Integer.parseInt(adjList[i])-1;
						
						if( g2.getvertex(v1).getadjList().contains(g2.getvertex(v2))) {
							continue;
						}
						g2.addEdge(g2.getvertex(v1), g2.getvertex(v2));		
					}	
				}
				buff.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			crossingEdges = g2.computeMinCut();
			allCuts.add(new Integer(crossingEdges));
			
			if( j%25 == 0) System.out.println(j);
		}	
		int minCut = Integer.MAX_VALUE;
		
		for(Integer cut : allCuts) {
			if(cut < minCut) {
				minCut = cut;
			}
		}
		System.out.println(minCut);
		*/
		
		// Coursera Week 4 Programming Problem
		/*
		Graph_Directed g3 = new Graph_Directed(875714);
		
		try {
			File f = new File("SCC.txt");
			FileReader fr = new FileReader(f);
			BufferedReader reader = new BufferedReader(fr);
			
			String line;
			String[] edge;
			while((line = reader.readLine()) != null) {
				edge = line.split(" ");
				g3.addEdge( g3.getvertex(Integer.parseInt(edge[0])-1), g3.getvertex(Integer.parseInt(edge[1])-1) );
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		g3.StronglyConnectedComponents();
		*/
		
		/*
		Graph g4 = new Graph(200);
		
		try {
			File f = new File("dijkstraData.txt");
			FileReader fr = new FileReader(f);
			BufferedReader reader = new BufferedReader(fr);
			
			String line;
			String[] edges;
			String[] e_weight;
			
			while( (line = reader.readLine()) != null) {
				edges = line.split("\t");
				
				for(int i=1; i<edges.length; i++) {
					e_weight = edges[i].split(",");
						
					int v1 = Integer.parseInt(edges[0])-1;
					int v2 = Integer.parseInt(e_weight[0])-1;
					
					if( g4.getvertex(v1).getadjList().contains(g4.getvertex(v2))) {
						continue;
					}
							
					g4.addEdge(g4.getvertex(v1), g4.getvertex(v2), Integer.parseInt(e_weight[1]));
				}
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		g4.DjikstraShortestPath(0);
		*/
		
		
	}
		
}
