package graphs;

public class Graph_2_Demo {

	public static void main(String[] args) {
		/*Graph_2 graph = new Graph_2(9);
		
		graph.addEdge(1, 2, 4);
		graph.addEdge(1, 8, 8);
		graph.addEdge(2, 8, 11);
		graph.addEdge(2, 3, 8);
		graph.addEdge(3, 9, 2);
		graph.addEdge(3, 4, 7);
		graph.addEdge(3, 6, 4);
		graph.addEdge(4, 5, 9);
		graph.addEdge(4, 6, 14);
		graph.addEdge(5, 6, 10);
		graph.addEdge(6, 7, 2);
		graph.addEdge(7, 8, 1);
		graph.addEdge(7, 9, 6);
		graph.addEdge(8, 9, 7);
		
		graph.primsMinimumSpanningTree();
		*/
		
		Graph_Directed_2 graph = new Graph_Directed_2(5);
		
		graph.addEdge(1, 2, 10);
		graph.addEdge(1, 5, 5);
		graph.addEdge(2, 3, 1);
		graph.addEdge(2, 5, 2);
		graph.addEdge(3, 4, 4);
		graph.addEdge(4, 1, 7);
		graph.addEdge(4, 3, 6);
		graph.addEdge(5, 2, 3);
		graph.addEdge(5, 3, 9);
		graph.addEdge(5, 4, 2);
		
		graph.DijkstraShortestPath(1);
	}

}
