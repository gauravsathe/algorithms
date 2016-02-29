package ctci;

import java.util.*;

public class Recursion_DP {
	public static void main(String[] args) {
		System.out.println("No of ways of climbing 20 stairs = " + Recursion_DP.climbStaircase(20));
		
		System.out.println("Magic index = " + Recursion_DP.magicIndex(new int[]{1,2,3,5,5,5,8,9}, 0, 7));
		
		System.out.println("All subsets :");
		Set<Integer> set = new HashSet<Integer>();
		set.add(new Integer(5));
		set.add(new Integer(6));
		set.add(new Integer(7));
		List<Set<Integer>> subsets = Recursion_DP.allSubsets(set);
		for(Set<Integer> s : subsets) {
			for(Integer e : s) {
				System.out.print(e.intValue());
			}
			System.out.println();
		}
		
		String s = "abc";
		System.out.print(String.format("All permutations of \"%s\" : ", s));
		List<String> all_perms = Recursion_DP.permutations(s);
		for(String p : all_perms) System.out.print(p + "  ");
		System.out.println();
		
		System.out.print(String.format("All n-parentheses with n = %d :",3));
		List<String> parentheses = Recursion_DP.validParentheses(3);
		for(String q : parentheses) {
			System.out.print(q + "  ");
		}
		System.out.println();
		
		System.out.println(String.format("No of ways to represent %d cents = %d", 6, Recursion_DP.representCents(6, new int[]{1,5,10,25})));
		
		//System.out.println("Ways of placing 8 queens on a boards : ");
		//Recursion_DP.nQueens(8);
		
	}
	
	
	public static int climbStaircase(int n) {
		int[] noOfWays = new int[n+1];
		
		noOfWays[0] = 0;
		noOfWays[1] = 1;
		noOfWays[2] = 2;
		noOfWays[3] = 4;
		
		if(n <= 3) return noOfWays[n];
		
		for(int i=4; i<=n; i++) {
			noOfWays[i] = noOfWays[i-1] + noOfWays[i-2] + noOfWays[i-3];
		}
		
		return noOfWays[n];
	}
	
	public static int magicIndex(int[] array) {
		return Recursion_DP.magicIndex(array, 0, array.length-1);
	}
	
	public static int magicIndex(int[] array, int start, int end) {
		if(start > end) return -1;
		
		int mIndex = -1;
		
		int mid = start + (end-start)/2;
		
		if(array[mid] == mid) return mid;
		else if(array[mid] > mid) {
			mIndex = Recursion_DP.magicIndex(array, start, mid-1);
			
			if(mIndex != -1) return mIndex;
			
			mIndex = Recursion_DP.magicIndex(array, array[mid], end);
		}
		else {
			mIndex = Recursion_DP.magicIndex(array, mid+1, end);
			
			if(mIndex != -1) return mIndex;
			
			mIndex = Recursion_DP.magicIndex(array, start, array[mid]);
		}
		
		return mIndex;
	}
	
	public static List<Set<Integer>> allSubsets(Set<Integer> set) {
		
		List<Set<Integer>> subsets = new ArrayList<Set<Integer>>();
		
		int noOfSubsets = (int)Math.pow(2, set.size());
		
		Object[] setArray = set.toArray();
		
		for(int i=0; i<noOfSubsets; i++) {
			Set<Integer> subset_curr = new HashSet<Integer>();
			
			for(int j=0; j<setArray.length; j++) {
				if((i & (1 << j)) != 0) {
					subset_curr.add((Integer)setArray[setArray.length-1-j]);
				}
			}
			
			subsets.add(subset_curr);
 		}
		
		return subsets;
	}
	
	public static List<String> permutations(String s) {
		List<String> all_perms = new ArrayList<String>();
		
		if(s == null) {
			return all_perms;
		}
		
		char[] chars = s.toCharArray();
		
		Recursion_DP.computePermutation(chars, 0, chars.length-1, all_perms);
		
		return all_perms;
	}
	
	public static void computePermutation(char[] chars, int start, int end, List<String> all_perms) {
		if(start == end) {
			all_perms.add(String.copyValueOf(chars));
			return;
		}
		
		for(int i=start; i<=end; i++) {
			Recursion_DP.swap(chars, start, i);
			
			Recursion_DP.computePermutation(chars, start+1, end, all_perms);
			
			Recursion_DP.swap(chars, start, i);
		}
	}
	
	public static void swap(char[] array, int i, int j) {
		char o = array[i];
		array[i] = array[j];
		array[j] = o;
	}
	
	public static List<String> validParentheses(int n) {
		List<String> parentheses = new ArrayList<String>();
		
		if(n == 0) {
			return parentheses;
		}
		
		int open_count = 0, close_count = 0;
		String paren_curr = "";
		
		Recursion_DP.generateParentheses(parentheses, paren_curr, open_count, close_count, n);
		
		return parentheses;
		
	}
	
	public static void generateParentheses(List<String> parentheses, String paren_curr, int open_count, int close_count, int n) {
		if(close_count == n) {
			parentheses.add(paren_curr);
			return;
		}
		
		if(open_count < n) {
			// open && close
			paren_curr += "(" ;
			Recursion_DP.generateParentheses(parentheses, paren_curr, open_count+1, close_count, n);
			
			if(open_count > close_count) {
				paren_curr = paren_curr.substring(0,paren_curr.length()-1) + ")" ;
				Recursion_DP.generateParentheses(parentheses, paren_curr, open_count, close_count+1, n);
			}
		}
		else {
			//only close
			paren_curr += ")" ;
			Recursion_DP.generateParentheses(parentheses, paren_curr, open_count, close_count+1, n);
		}
	}
	
	public static int representCents(int n, int[] coins) {
		
		if(n == 0) {
			return 1;
		}
		
		int m = coins.length;
		// no of ways = no of ways without coins[m-1] + no of ways with atleast one of coins[m-1]
		return Recursion_DP.coinChange(n, coins, m-1);
			
	}
	
	public static int coinChange(int n, int[] coins, int coin_index) {
		
		if(n == 0) {
			return 1;
		}
		
		if(n < 0) {
			return 0;
		}
		
		if(coin_index < 0 && n > 0) {
			return 0;
		}
		
		return coinChange(n, coins, coin_index-1) + coinChange(n - coins[coin_index], coins, coin_index);
	}
	
	public static void nQueens(int n) {
		if(n == 0) {
			return;
		}
		
		int[][] board = new int[n][n];
		
		Recursion_DP.computeBoard(board, 0, n);
	}
	
	public static void computeBoard(int[][] board, int row, int n) {
		if(row == n) {
			Recursion_DP.printBoard(board,n);
			return;
		}
		
		
		for(int j=0; j<n; j++) {
			board[row][j] = 1;
			
			if(!conflict(board, row, j, n)) {
				Recursion_DP.computeBoard(board, row+1, n);
			}
			board[row][j] = 0;
		}
	}
	
	public static boolean conflict(int[][] board, int i, int j, int n) {
		int row = 0, col = 0;
		//System.out.println("Checking conflict ...");
		//Recursion_DP.printBoard(board, n);
		col = j;
		for(row = i-1; row >= 0; row--) {
			if(board[row][col] == 1) return true;
		}
		
		row = i-1;
		col = j-1;
		while(row >=0 && col >= 0) {
			if(board[row--][col--] == 1) return true;
			
		}
		
		row = i-1;
		col = j+1;
		while(row >=0 && col < n) {
			if(board[row--][col++] == 1) return true;
		}
		
		return false;
	}
	
	public static void printBoard(int[][] board, int n) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("-----------------------------");
	}
}












