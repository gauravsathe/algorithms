package IOHandling;

import java.io.*;
import java.util.Scanner;

public class ScannerTest {

	public static void main(String[] args) {
		Scanner s = null;
		
		try {
			s = new Scanner(new BufferedReader(new FileReader("ioSample.txt")));
			
			s.useDelimiter("\n");
			
			while(s.hasNext()) {
				System.out.println(s.next());
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(s != null) {
				s.close();
			}
		}
		System.out.println();
		try {
			s = new Scanner("A stately pleasure-dome decree:");
			
			s.useDelimiter("-");
			
			while(s.hasNext()) {
				System.out.println(s.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(s != null) s.close();
		}

	}

}
