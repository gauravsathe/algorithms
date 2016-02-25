package ctci;
import java.util.*;

public class Arrays_Strings {

	public static void main(String[] args) {
		System.out.println(hasUniqueChars("sathe"));
		
		System.out.println(reverse("gaurav"));
		
		System.out.println(isAnagram("dog","gog"));
		
		Character[] str = {'M','r',' ','J','o','h','n',' ','S','m','i','t','h',' ',' ',' ',' '};
		replaceSpaces(str);
		for(int i=0; i<str.length; i++) System.out.print(str[i].charValue());
		
		System.out.println();
		System.out.println(compressString("aabcccccaaa"));
	}
	
	public static boolean hasUniqueChars(String s) {
		if(s == null) {
			return true;
		}
		
		int len = s.length();
		/*
		HashMap<Character,Boolean> hm = new HashMap<Character,Boolean>();
		
		for(int i=0; i<len; i++) {
			Character c = new Character(s.charAt(i));
			
			if(hm.get(c) != null) return false;
			
			hm.put(c, new Boolean(true));
		}
		
		return true;
		*/
		// Without using additional data structures
		for(int i=0; i<len-1; i++) {
			for(int j=i+1; j<len; j++) {
				if(s.charAt(i) == s.charAt(j)) {
					return false;
				}
			}
		}
		return true;
		
	}
	
	public static String reverse(String s) {
		if(s==null) return s;
		
		String rev = "";
		
		for(int i=s.length()-1; i>=0; i--) {
			rev += Character.toString(s.charAt(i));
		}
		return rev;
	}
	
	public static boolean arePalindromes(String s1) {
		if(s1 == null) {
			return false;
		}
		
		return s1.equals(reverse(s1));
	}
	
	public static boolean isAnagram(String s1, String s2) {
		if(s1 == null || s2 == null) {
			return false;
		}
		
		int len1 = s1.length();
		int len2 = s2.length();
		
		if(len1 != len2) return false;
		
		HashMap<Character,Integer> charCount = new HashMap<Character,Integer>();
		
		int i=0;
		
		for(i=0; i<len1; i++) {
			Character c = new Character(s1.charAt(i));
			try {
				charCount.put(c, charCount.get(c)+1);
			} catch( NullPointerException e) {
				charCount.put(c, 1);
			}
		}
		
		for(i=0; i<len2; i++) {
			Character c = new Character(s2.charAt(i));
			try {
				charCount.put(c, charCount.get(c)-1);
				if(charCount.get(c) == 0) {
					charCount.remove(c);
				}
			} catch(NullPointerException e) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void replaceSpaces(Character[] str) {
		int len = str.length;
		
		if(len == 0) return;
		
		int i = len-1;
		int j = len-1;
		
		while(str[i].charValue() == ' ') i--;
		
		while(i >= 0) {
			if(str[i].charValue() == ' ') {
				str[j--] = new Character('0');
				str[j--] = new Character('2');
				str[j--] = new Character('%');
			}
			else {
				str[j--] = str[i];
			}
			i--;
		}
	}
	
	public static String compressString(String s) {
		if(s == null) {
			return null;
		}
		
		int len = s.length();
		
		int i=0;
		Character currChar = s.charAt(i);
		int currCharCount = 1;
		String compressed = currChar.toString();
		
		for(i=1; i<len; i++) {
			if(s.charAt(i) == currChar.charValue()) {
				currCharCount += 1;
			}
			else {
				compressed += Integer.toString(currCharCount);
				
				currChar = s.charAt(i);
				compressed += currChar.toString();
				currCharCount = 1;
			}
		}
		
		compressed += Integer.toString(currCharCount);
		
		if(compressed.length() == 2*len) return s;
		else return compressed;
	}
	
	public static void setZero(int[][] matrix) {
		int M = matrix.length;
		int N = matrix[0].length;
		
		int[] zeroRows = new int[M];
		int[] zeroCols = new int[N];
		
		int i=0, j=0;
		
		for(i=0; i<M; i++) {
			for(j=0; j<N; j++) {
				if(matrix[i][j] == 0) {
					zeroRows[i] = 1;
					zeroCols[j] = 1;
				}
			}
		}
		
		for(i=0; i<M; i++) {
			for(j=0; j<N; j++) {
				if(zeroRows[i] == 1 || zeroCols[j] == 1) {
					matrix[i][j] = 0;
				}
			}
		}
	}
}
