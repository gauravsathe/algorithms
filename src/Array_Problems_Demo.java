import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;


public class Array_Problems_Demo {

	public static void main(String[] args) {
		
		/*
		int[] A = {1,2,1,3,4,2,3};
		
		Array_Problems p1 = new Array_Problems(A);
		
		p1.countDistinctIntsInWindow(4);
		*/
		
		HashMap<Long, Integer> hM = new HashMap<Long, Integer>();
		try {
			File f = new File("algo1-programming_prob-2sum.txt");
			BufferedReader buff = new BufferedReader(new FileReader(f));
			
			String line;
			
			while((line = buff.readLine()) != null) {
				hM.put(Long.parseLong(line), 1);
			}
			
			buff.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		int count = 0;
		for(Long i=(long) -10000; i<=10000; i++) {
			count += Array_Problems.twoSumProblem(hM, i);
			if(i%1000 == 0) {
				System.out.println(i);
			}
		}
		
		
		System.out.println(count);
	}

}
