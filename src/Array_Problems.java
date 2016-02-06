import java.math.BigInteger;
import java.util.HashMap;

public class Array_Problems {
	
	private int[] array;
	
	public Array_Problems(int[] a) {
		array = a;
	}
	
	public int[] getArray() {
		return array;
	}
	
	public void setarray(int[] newArray) {
		array = newArray;
	}
	
	public void countDistinctIntsInWindow(int k) {
		/* Given a window size 'k' count the number of distinct integers in each window */
		
		int n = array.length;
		int dist_count = 0;
		
		HashMap<Integer, Integer> hM = new HashMap<Integer, Integer>();
		
		for(int i=0; i<k; i++) {
			try {
				hM.put(array[i], hM.get(array[i])+1);
			} catch(NullPointerException e) {
				hM.put(array[i], 1);
				dist_count += 1;
			}
		}
		
		System.out.println(dist_count);
		
		for(int i=k; i<n; i++) {
			if(hM.get(array[i-k]) == 1) {
				hM.remove(array[i-k]);
				dist_count -= 1;
			}
			else {
				hM.put(array[i-k], hM.get(array[i-k])-1);
			}
			
			try {
				hM.put(array[i], hM.get(array[i])+1);
			} catch(NullPointerException e) {
				hM.put(array[i], 1);
				dist_count += 1;
			}
			
			System.out.println(dist_count);
		}
	}
	
	public static int twoSumProblem(HashMap<Long, Integer> hM, Long S ) {
		
		Long num1, num2;
		
		for(Long i : hM.keySet()) {
			num1 = i;
			num2 = S - num1;
			
			if((hM.get(num2) != null) ) {
				return 1;
			}
		}
		
		return 0;
	}
}
