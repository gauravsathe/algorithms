package graphs;

import java.util.ArrayList;

import Linked_List.Node_Main;

public class Vertex extends Node_Main {
	
	private int m_degree;
	private int m_number;
	private String m_color;
	private int finishTime;
	private ArrayList<Vertex> adjList;
	private Vertex shortestPathPredecessor;
	private Vertex dfsPredecessor;
	private int shortestPathDistance;
	private int incidentEdgeWeight;
	private int mstKey;
	private Vertex mstPred;
	
	public Vertex(int number) {
		m_number = number;
		m_degree = 0;
		m_color = "white";
		adjList = new ArrayList<Vertex>();
		shortestPathDistance = Integer.MAX_VALUE;
		shortestPathPredecessor = null;
		mstKey = Integer.MAX_VALUE;
		value = shortestPathDistance;
		setMstPred(null);
	}
	
	public boolean equals(Vertex v) {
		return m_number == v.getNumber();
	}
	
	public int hashCode() {
		Integer i = new Integer(m_number);
		return i.hashCode();
	}
	
	public int getNumber() {
		return m_number;
	}
	
	public int getDegree() {
		return m_degree;
	}
	
	public void increaseDegree() {
		m_degree += 1;
	}
	
	public void decreaseDegree() {
		m_degree -= 1;
	}

	public void setColor(String color) {
		m_color = color;
	}
	
	public String getColor() {
		return m_color;
	}
	
	public void setFinishTime(int time) {
		finishTime = time;
	}
	
	public int getFinishTime() {
		return finishTime;
	}
	
	public ArrayList<Vertex> getadjList() {
		return adjList;
	}
	
	public void addEdge(Vertex v) {
		adjList.add(v);
	}
	
 	public void setPred(Vertex pred) {
		shortestPathPredecessor = pred;
	}
	
	public Vertex getPred() {
		return shortestPathPredecessor;
	}
	
	public void setShortestPathDistance(int dist) {
		shortestPathDistance = dist;
		value = shortestPathDistance;
	}
	
	public int getShortestPathDistance() {
		return shortestPathDistance;
	}
	
	public void setDFSPredecessor(Vertex v) {
		dfsPredecessor = v;
	}
	
	public Vertex getDFSPredecessor() {
		return dfsPredecessor;
	}
	
	public int getMSTKey() {
		return mstKey;
	}
	
	public void setMSTKey(int key) {
		mstKey = key;
		value = mstKey;
	}
	
	public void printList() {
		System.out.print("Vertex " + ((int)getNumber()+1) + "--->");
		for( Vertex v : adjList) {
			System.out.print(((int)v.getNumber()+1) + "\t");
		}
		System.out.println();
		
	}

	public Vertex getMstPred() {
		return mstPred;
	}

	public void setMstPred(Vertex mstPred) {
		this.mstPred = mstPred;
	}
}
