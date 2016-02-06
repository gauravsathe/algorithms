package graphs;

public class Edge implements Comparable<Edge>{
	private Vertex v1, v2;
	private int edgeWeight;
	
	public Edge(Vertex u, Vertex v) {
		this(u, v, 1);
	}
	
	public Edge(Vertex u, Vertex v, int weight) {
		v1 = u;
		v2 = v;
		edgeWeight = weight;
	}
	
	public boolean equals(Edge e) {
		return (v1.equals(e.getVertex1()) && v2.equals(e.getVertex2())) || (v1.equals(e.getVertex2()) && v2.equals(e.getVertex1()));
	}
	public Vertex getVertex1() {
		return v1;
	}
	
	public Vertex getVertex2() {
		return v2;
	}
	
	public void setEdgeWeight(int newWeight) {
		edgeWeight = newWeight;
	}
	
	public int getEdgeWeight() {
		return edgeWeight;
	}
	
	public void printEdge() {
		System.out.println("(" + ((int)(v1.getNumber())+1) + "," + ((int)(v2.getNumber())+1) + "," + edgeWeight + ")");
	}

	
	@Override
	public int compareTo(Edge e) {
		if(this.getEdgeWeight() <= e.getEdgeWeight()) return -1;
		else return 1;
		
	}
}
