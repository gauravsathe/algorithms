package ExceptionHandling;

import java.util.*;
import java.io.*;

public class ListOfNumbers {
	private static List<Integer> list;
	private static final int SIZE = 10;
	
	public ListOfNumbers() {
		list = new ArrayList<Integer>(SIZE);
		
		for(int i=0; i<9; i++) {
			list.add(new Integer(i));
		}
	}
	
	public static void writeList() {
		PrintWriter pwriter = null;
		try {
			pwriter = new PrintWriter(new FileWriter("output.txt"));
			for(int i=0; i<9; i++) {
				pwriter.println("Value at index " + i + " = " + list.get(i).intValue());
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if(pwriter != null) {
				pwriter.close();
			}
		}	
	}
	
	public static void main(String[] args) {
		ListOfNumbers list = new ListOfNumbers();
		list.writeList();
	}
}
