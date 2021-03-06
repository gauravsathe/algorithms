package bitMagic;

import java.security.InvalidParameterException;

public class Bit_Magic {

	public static void main(String[] args) {
		System.out.println(nextPowerOf2(17));
		
		System.out.println(checkEvenParity(12));
		
		System.out.println(positionOfFirstOne(16));
		
		int[] a = {2, 3, 5, 4, 5, 2, 4, 3, 5, 2, 4, 4, 2};
		System.out.println(findNumberOccuringOddNumberOfTimes(a));
		
		System.out.println(reverseBits(4));
		
		int b = Integer.MAX_VALUE;
		System.out.println(((long)b)+1);
	}
	
	public static int nextPowerOf2(int k) {
		//return (int)Math.pow(2, Math.ceil(Math.log(k)/Math.log(2)));
		
		if( (k & (k-1)) == 0) {
			return k;
		}
		
		int power = 1;
		while(power < k) {
			power = power << 1;
		}
		
		return power;
	}
	
	public static boolean checkEvenParity(int k) {
		int parity = 1;
		
		while(k != 0) {
			k = k & (k-1);
			parity = ~parity;
		}
		
		return (parity == 1);
	}
	
	public static int positionOfFirstOne(int k) {
		/*
		 * Given k.... 2's complement of k i.e. (-k) inverts all bits of k from left to right till the first 1 from the right.
		 * Eg. 18 = 010010,   -18 = 101110
		 */
		
		return (int)(Math.log(k & (-k))/Math.log(2)) + 1; 
	}
	
	public static int findNumberOccuringOddNumberOfTimes(int[] nums) {
		int xor = 0;
		
		for(int i=0; i<nums.length; i++) {
			xor = xor ^ nums[i];
		}
		
		return xor;
	}
	
	public static int reverseBits(int k) {
		int rev = 0;
		
		for(int i=0; i<Integer.SIZE; i++) {
			if( (k & (1 << i)) != 0) {
				rev = rev | (1 << (Integer.SIZE-i));
			}
		}
		
		return rev;
	}
	
	public static int charToInt(Character c) {
		if(c >= '0' && c <= '9') return c - '0';
		else if(c >= 'A' && c <= 'F') return c - 'A' + 10;
		else if(c >= 'a' && c <= 'f') return c - 'a' + 10;
		else {
			return -1;
		}
	}
	
	public static int convertToBase(String num, int base) {
		if((base < 2) || (base > 10 && base != 16)) return -1;
		
		int len = num.length();
		int result = 0;
		
		for(int i=len-1; i>=0; i--) {
			int digit = Bit_Magic.charToInt(new Character(num.charAt(i)));
			
			if(digit == -1 || digit >= base) return -1;
			
			int exp = len-1 - i;
			
			result += digit * (int)Math.pow(base, exp);
		}
		return result;
		
	}
}
