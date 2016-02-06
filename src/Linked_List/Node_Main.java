package Linked_List;

import java.io.*;

public class Node_Main implements Serializable {
	
	protected int value;
	private String name;
	
	public Node_Main() {
		value = 0;
		name = "Some Node";
	}
	
	public Node_Main(int val) {
		value = val;
		name = "Some Node";
	}
	
	public Node_Main(int val, String n) {
		value = val;
		name = n;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int new_value) {
		value = new_value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String new_name) {
		name = new_name;
	}
}
