package ctci;

public class Bit_Manipulation {

	public static void main(String[] args) {
		int n = 1024;
		int m = 19;
		
		System.out.println(Integer.toBinaryString(Bit_Manipulation.insertNumber(m, n, 6, 2)));
		
		System.out.println(Bit_Manipulation.doubleToBinary(0.80));
		
		int a=423, b=10561;
		System.out.println(String.format("Bits needed to convert %d into %d = %d", a, b, Bit_Manipulation.convertInts(a, b)));
		
		int num = 30;
		System.out.println(String.format("%d after swapping odd-even bits = %d", num, Bit_Manipulation.swapEvenOddBits(num)));
	}
	
	public static int insertNumber(int m, int n, int i, int j) {
		int mask = ~((1 << (i+1)) - 1) ^ ((1 << (j)) - 1) ;
		
		n = n & mask;
		
		n = n ^ (m << j);
		
		return n;
	}
	
	public static String doubleToBinary(double d) {
		if(d == 0) {
			return "0.0";
		}
		
		String res = "0.";
		
		int i=1;
		while(d != 0.0 && i <= 64) {
			double exp = 1.0 / (1 << i);
			
			if(d >= exp) {
				res += "1";
				d -= exp;
			}
			else {
				res += "0";
			}
			
			i += 1;
		}
		
		if(i > 32) {
			return "ERROR";
		}
		
		return res;
	}
	
	public static int convertInts(Integer a, Integer b) {
		if(a == b) {
			return 0;
		}
		
		int c = a.intValue() ^ b.intValue();
		
		int count = 0;
		for(int i=0; i<32; i++) {
			if( (c & (1 << i)) != 0) count += 1;
		}
		
		return count;
	}
	
	public static int swapEvenOddBits(int num) {
		if(num == 0) return num;
		
		int i=0;
		int mask = 3;
		
		for(i=0; i<32; i+=2) {
			int bit_1 = num & (1 << i);
			int bit_2 = num & (1 << (i+1));
			
			if((bit_1 == 0 && bit_2 != 0)|| (bit_1 != 0 && bit_2 == 0)) {
				num = num ^ (mask << i);
			}
		}
		
		return num;
	}
}
