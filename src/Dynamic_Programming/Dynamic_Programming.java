package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.text.DefaultEditorKit.CutAction;

import Binary_Search_Tree.BST;
import Binary_Search_Tree.Tree_Node;

public class Dynamic_Programming {
	
	public static int[] optimal_soln;
	
	public static void Max_Size_Independent_Set(int[] path_graph) {
		optimal_soln = new int[path_graph.length];
		
		optimal_soln[0] = path_graph[0];
		optimal_soln[1] = Math.max(path_graph[0], path_graph[1]);
		
		for(int i=2; i<path_graph.length; i++) {
			optimal_soln[i] = Math.max(optimal_soln[i-2] + path_graph[i], optimal_soln[i-1]);
		}
		
		System.out.println("Value of max size independent set = " + optimal_soln[path_graph.length-1]);
		
		// Print the independent set
		System.out.print("Vertices in independent set : ");
		int i=optimal_soln.length-1;
		while( i>1 ) {
			if(optimal_soln[i-2] + path_graph[i] > optimal_soln[i-1]) {
				System.out.print(i+", ");
				i -= 2;
			}
			else {
				i -= 1;
			}
		}
		
		if(i == 1) {
			if(optimal_soln[i] > optimal_soln[i-1]) {
				System.out.println(i);
			}
		}
		if(i == 0 ) {
			System.out.println(i);
		}
	}
	
	public static void Max_Size_Independent_Set_BST(BST tree) {
		
		int[] optimal_solution = new int[tree.getSize()];
		
		int i=0;
		
		for(i=0; i<tree.getSize(); i++) {
			optimal_solution[i] = -1;
		}
		
		
	}
	
	public static void MSIS_BST_Memoization(BST tree, Tree_Node root, int[] optimal_solution) {
		
		int size = tree.size(root.getLeft());
		
	}
	
	public static void Knapsack_Problem(int[] values, int[] weights, int knapsack_size, int no_of_items) {
		
		int[][] optimal_solution = new int[no_of_items+1][knapsack_size+1];
		
		int i=0, j=0;
		
		for(i=0; i<=no_of_items; i++) {
			optimal_solution[i][0] = 0;
		}
		
		for(j=0; j<=knapsack_size; j++) {
			optimal_solution[0][j] = 0;
		}
		
		for(i=1; i<= no_of_items; i++) {
			for(j=1; j<=knapsack_size; j++) {
				// If size of knapsack is less than the weight[i] then item 'i' cannot be put in knapsack
				if( j < weights[i-1] ) {
					optimal_solution[i][j] = 0;
				}
				else {
					optimal_solution[i][j] = Math.max(optimal_solution[i-1][j], optimal_solution[i-1][j-weights[i-1]] + values[i-1]);
				}
			}
		}
		
		System.out.println(optimal_solution[no_of_items][knapsack_size]);
		
		// Reconstruct Solution
		System.out.print("Items in knapsack : ");
		j = knapsack_size;
		for(i=no_of_items; i>0; i--) {
			if(weights[i-1] > j) {
				continue;
			}
			if(optimal_solution[i-1][j] < (optimal_solution[i-1][j-weights[i-1]] + values[i-1])) {
				System.out.print(i + " ");
				j -= weights[i-1];
			}
		}
		System.out.println();
	}
	
	public static void Sequence_Alignment(char[] seq1, char[] seq2, int gap_penalty, int mismatch_penalty, int match_score) {
		
		int[][] optimal_solution = new int[seq1.length+1][seq2.length+1];
		
		int i=0, j=0;
		for(i=0; i<=seq1.length; i++) {
			optimal_solution[i][0] = i*gap_penalty;
		}
		
		for(j=0; j<=seq2.length; j++) {
			optimal_solution[0][j] = j*gap_penalty;
		}
		
		for(i=1; i<=seq1.length; i++) {
			for(j=1; j<=seq2.length; j++) {
				if(seq1[i-1] == seq2[j-1]) {
					optimal_solution[i][j] = optimal_solution[i-1][j-1] + match_score;
				}
				else {
					optimal_solution[i][j] = Math.max(Math.max(optimal_solution[i-1][j]+gap_penalty, optimal_solution[i][j-1]+gap_penalty), optimal_solution[i-1][j-1] + mismatch_penalty);
				}
			}
		}
		
		System.out.println(optimal_solution[seq1.length][seq2.length]);
		
		// Reconstruct solution
		char[] seq1_aligned = new char[seq1.length+seq2.length];
		char[] seq2_aligned = new char[seq1.length+seq2.length];
		int k=0;
		i = seq1.length;
		j = seq2.length;
		while(i>0 && j>0) {
			if((optimal_solution[i][j] == (optimal_solution[i-1][j-1] + match_score)) || (optimal_solution[i][j] == (optimal_solution[i-1][j-1] + mismatch_penalty))) {
				seq1_aligned[k] = seq1[i-1];
				seq2_aligned[k] = seq2[j-1];
				i -= 1;
				j -= 1;
			}
			else if( optimal_solution[i][j] == (optimal_solution[i-1][j]+gap_penalty)) {
				seq1_aligned[k] = seq1[i-1];
				seq2_aligned[k] = '_';
				i -= 1;
			}
			else if( optimal_solution[i][j] == (optimal_solution[i][j-1]+gap_penalty)) {
				seq1_aligned[k] = '_';
				seq2_aligned[k] = seq2[j-1];
				j -= 1;
			}
			k += 1;
 		}	
		
		while(i>0) {
			seq1_aligned[k] = seq1[i-1];
			seq2_aligned[k] = '_';
			i -= 1;
			k += 1;
		}
		while(j>0) {
			seq1_aligned[k] = '_';
			seq2_aligned[k] = seq2[j-1];
			j -= 1;
			k += 1;
		}
		
		for(i=k-1;i>=0;i--) System.out.print(seq1_aligned[i]);
		System.out.println();
		for(i=k-1;i>=0;i--) System.out.print(seq2_aligned[i]);
		System.out.println();
		
	}
	
	public static void Max_Sum_Subarray(int[] array) {
		
		int current_sum = -999999;//Integer.MIN_VALUE;
		int global_sum = -999999; //Integer.MIN_VALUE;
		
		int current_start = -1, current_end = -1, global_start = -1,global_end = -1;
		
		for(int i=0; i<array.length; i++) {
			// Check if current sum is improved by appending A[i]
			if((array[i] + current_sum) > array[i]) {
				current_sum += array[i];
				current_end = i;
			}
			else {
				current_sum = array[i];
				current_start = i;
				current_end = i;
			}
			
			// Check if current sum is the overall max sum
			if( current_sum > global_sum ) {
				global_sum = current_sum;
				global_start = current_start;
				global_end = current_end;
			}
		}
		
		System.out.println("Max sum subbarray is from index " + global_start +" to " + global_end +" and has sum " + global_sum);
	}
	
	public static void Ugly_Numbers(int n) {
		
		int[] ugly_numbers = new int[n];
		
		int i2 = 1, i3 = 1, i5 = 1;
		int m2 = i2*2, m3 = i3*3 , m5 = i5*5;
		
		int i = 0;
		ugly_numbers[i++] = 1;
		
		while(i<n) {
			ugly_numbers[i] = Math.min(Math.min(m2, m3), m5);
			
			if(m2 == ugly_numbers[i]) {
				i2 += 1;
				m2 = i2*2;
			}
			if(m3 == ugly_numbers[i]) {
				i3 += 1;
				m3 = i3*3;
			}
			if(m5 == ugly_numbers[i]) {
				i5 += 1;
				m5 = i5*5;
			}
			
			i += 1;
		}
		
		System.out.println(ugly_numbers[n-1]);
	}
	
	public static void Longest_Common_Subsequence(char[] seq1, char[] seq2) {
		
		int[][] lcs = new int[seq1.length+1][seq2.length+1];
		
		int i=0,j=0;
		
		for(i=0; i<=seq1.length; i++) {
			lcs[i][0] = 0;
		}
		for(j=0; j<=seq2.length; j++) {
			lcs[0][j] = 0;
		}
		
		for(i=1; i<=seq1.length; i++) {
			for(j=1; j<=seq2.length; j++) {
				if(seq1[i-1] == seq2[j-1]) {
					lcs[i][j] = lcs[i-1][j-1] + 1;
				}
				else {
					lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
		
		System.out.println("Length of LCS = " + lcs[seq1.length][seq2.length]);
		
		// Reconstruct solution
		i=seq1.length;
		j=seq2.length;
		while(i>0 && j>0) {
			if(seq1[i-1] == seq2[j-1]) {
				System.out.print(seq1[i-1]);
				i--;
				j--;
			}
			else if( lcs[i-1][j] > lcs[i][j-1] ) {
				i--;
			}
			else {
				j--;
			}
		}
		System.out.println();
	}
	
	public static void Edit_Distance(char[] s1, char[] s2) {
		
		int l1 = s1.length;
		int l2 = s2.length;
		
		int[][] optimal_solution = new int[l1+1][l2+1];
		
		int i=0,j=0;
		
		for(i=0; i<=l1; i++) {
			optimal_solution[i][0] = i;
		}
		for(j=0; j<=l2; j++) {
			optimal_solution[0][j] = j;
		}
		
		for(i=1; i<=l1; i++) {
			for(j=1; j<=l2; j++) {
				if(s1[i-1] == s2[j-1]) {
					optimal_solution[i][j] = optimal_solution[i-1][j-1];
				}
				else {
					optimal_solution[i][j] = Math.min(Math.min(optimal_solution[i-1][j-1]+1, optimal_solution[i][j-1]+1), optimal_solution[i-1][j]+1);
				}
			}
		}
		
		System.out.println("No of edits = " + optimal_solution[l1][l2]);
		
	}
	
	public static void Min_Cost_Path(int[][] cost, int m, int n) {
		int l1 = cost.length;
		int l2 = cost[0].length;
		
		int[][] optimal_solution = new int[l1][l2];
		
		int i=0,j=0;
		
		optimal_solution[0][0] = cost[0][0];
		for(i=1; i<l1; i++) {
			optimal_solution[i][0] = optimal_solution[i-1][0] + cost[i][0];
		}
		for(j=1; j<l2; j++) {
			optimal_solution[0][j] = optimal_solution[0][j-1] + cost[0][j];
		}
		
		for(i=1; i<=m; i++) {
			for(j=1; j<=n; j++) {
				optimal_solution[i][j] = Math.min(Math.min(optimal_solution[i][j-1], optimal_solution[i-1][j]), optimal_solution[i-1][j-1]) + cost[i][j];
			}
		}
		
		System.out.println("Cost of min-cost-path = " + optimal_solution[m][n]);
	}
	
	public static void Lon_Substr_Without_Repeat(String str) {
		
		HashMap<Character, Integer> last_visited = new HashMap<Character, Integer>();
		
		int[] optimal_solution = new int[str.length()];
		
		int global_start = 0, global_end = 0, current_start = 0, current_end = 0;
		
		optimal_solution[0] = 1;
		last_visited.put(str.charAt(0), 0);
		
		for(int i=1; i<str.length(); i++) {
			if(last_visited.get(str.charAt(i)) == null) {
				current_end = i;
				last_visited.put(str.charAt(i), i);
			}
			else {
				if( last_visited.get(str.charAt(i)) < (i-optimal_solution[i-1]) ) {
					current_end = i;
					last_visited.put(str.charAt(i), i);
				}
				else {
					current_start = current_end = i;
					last_visited.put(str.charAt(i), i);
				}
			}
			
			optimal_solution[i] = current_end-current_start+1;
			
			if( current_end-current_start+1 > global_end-global_start+1) {
				global_start = current_start;
				global_end = current_end;
			}
		}
		
		System.out.println("Longest substring without repeating characters = " + str.substring(global_start, global_end+1));
	}
	
	public static void Min_Jumps(int[] jumps) {
		
		int[] optimal_solution = new int[jumps.length];
		int[] optimal_jump = new int[jumps.length];
		
		optimal_solution[jumps.length-1] = 0;
		
		int i=0,j=0;
		
		for(i=jumps.length-2; i>=0; i--) {
			int min_jumps = jumps.length-i;
			for(j=1; j<=jumps[i]; j++) {
				if( i+j >= jumps.length-1 ) {
					min_jumps = 1;
					optimal_jump[i] = j;
					break;
				}
				else {
					if( 1 + optimal_solution[i+j] < min_jumps) {
						min_jumps = 1+optimal_solution[i+j];
						optimal_jump[i] = j;
					}
				}
			}
			optimal_solution[i] = min_jumps;
		}
		
		System.out.println("Minimum number of jumps = " + optimal_solution[0]);
		// Reconstruct Solution
		for(i=0; i!=jumps.length-1;i+=optimal_jump[i]) {
			System.out.print(jumps[i] + "->");
		}
		System.out.println(jumps[i]);
	}
	
	public static void Binomial_Coefficient(int n, int k) {
		int[][] optimal_solution = new int[n+1][n+1];
		
		int i=0, j=0;
		
		for(i=1;i<=n;i++) {
			optimal_solution[i][0] = 1;
		}
		for(j=0; j<=n; j++) {
			optimal_solution[0][j] = 0;
		}
		
		for(i=1; i<=n; i++) {
			for(j=1; j<i; j++) {
				optimal_solution[i][j] = optimal_solution[i-1][j-1] + optimal_solution[i-1][j];
			}
			optimal_solution[i][j] = 1;
		}
		
		System.out.println("nCk = " + optimal_solution[n][k]);
	}
	
	public static void Longest_Palindromic_Subsequence(String str) {
		int[][] optimal_solution = new int[str.length()][str.length()];
		
		int i=0, j=0;
		
		for(i=0; i<str.length(); i++) {
			for(j=0;j<i; j++) {
				optimal_solution[i][j] = 0;
			}
			optimal_solution[i][j] = 1;
		}
		
		for(i=str.length()-1;i>=0;i--) {
			for(j=i+1; j<str.length(); j++) {
				if(str.charAt(i) == str.charAt(j)) {
					optimal_solution[i][j] = optimal_solution[i+1][j-1] + 2;
				}
				else {
					optimal_solution[i][j] = Math.max(optimal_solution[i][j-1], optimal_solution[i+1][j]);
				}
			}
		}
		
		System.out.println("Length of longest palindromic subsequence = " + optimal_solution[0][str.length()-1]);
		
		// Reconstructing solution
		char[] solution = new char[optimal_solution[0][str.length()-1]];
		int k=0;
		i=0;
		j=str.length()-1;
		while(i<=j) {
			if(str.charAt(i) == str.charAt(j)) {
				solution[k] = solution[solution.length-1-k] = str.charAt(i);
				i += 1;
				j -= 1;
				k += 1;
			}
			else {
				if(optimal_solution[i][j-1] > optimal_solution[i+1][j]) {
					j -= 1;
				}
				else {
					i += 1;
				}
			}
		}
		
		for(i=0; i<solution.length; i++) {
			System.out.print(solution[i]);
		}
		System.out.println();
	}
	
	public static void Rod_Cutting(int[] prices) {
		
		int[] optimal_solution = new int[prices.length+1];
		int[] optimal_cut = new int[prices.length+1];
		
		optimal_solution[0] = 0;
		optimal_cut[0] = 0;
		
		int i=0, j=0;
		
		for(i=1; i<=prices.length; i++) {
			int max_price = prices[i-1];
			optimal_cut[i] = i;
			for(j=1; j<=i/2; j++) {
				if( optimal_solution[j] + optimal_solution[i-j] > max_price) {
					max_price = optimal_solution[j] + optimal_solution[i-j];
					optimal_cut[i] = j;
				}
			}
			optimal_solution[i] = max_price;
		}
		
		System.out.println("Max price for cuting rod = " + optimal_solution[prices.length]);
	}
	
	public static void Max_Sum_Inc_Subsequence(int[] array) {
		int[] optimal_solution = new int[array.length];
		
		int i=0, j=0;
		
		for(i=0; i<array.length; i++) {
			optimal_solution[i] = array[i];
		}
		
		for(i=1; i<array.length; i++) {
			for(j=0; j<i; j++) {
				if( (array[i] > array[j]) && (optimal_solution[j] + array[i] > optimal_solution[i])) {
					optimal_solution[i] = optimal_solution[j] + array[i];
				} 
			}
		}
		
		int max_sum_seq = 0;
		for(i=0; i<array.length; i++) {
			if(optimal_solution[i] > max_sum_seq) {
				max_sum_seq = optimal_solution[i];
			}
		}
		System.out.println("Sum of max sum increasing subsequence = " + max_sum_seq);
	}
	
	public static void Longest_Bitonic_Subsequence(int[] array) {
		
		int[] LIS = new int[array.length];
		int[] LIS_soln = new int[array.length];
		int[] LDS = new int[array.length];
		int[] LDS_soln = new int[array.length];
		
		int i=0, j=0;
		
		// First calculate the Longest increasing subsequence for each element of array
		for(i=0; i<array.length; i++) {
			LIS[i] = 1;
		}
		for(i=1; i<array.length; i++) {
			for(j=0; j<i; j++) {
				if((array[i] > array[j]) && (LIS[j]+1 > LIS[i])) {
					LIS[i] = LIS[j] + 1;
					LIS_soln[i] = j;
				}
			}
		}
		
		// Next calculate the Longest decreasing subsequence starting from each element of array i.e. LIS looking at the array in reverse
		for(j=0; j<array.length; j++) {
			LDS[j] = 1;
		}
		for(j=array.length-2; j>=0; j--) {
			for(i=array.length-1; i>j; i--) {
				if((array[j] > array[i]) && (LDS[i] + 1 > LDS[j])) {
					LDS[j] = LDS[i] + 1;
					LDS_soln[j] = i;
				}
			}
		}
		
		int[] LBS = new int[array.length];
		// Calculate Longest bitonic subsequence at element 'i' as LIS[i] + LDS[i]
		for(i=0; i<array.length; i++) {
			LBS[i] = LIS[i] + LDS[i] - 1;
		}
		
		// Length of LBS is max of LBS[]
		int lbs = 0;
		int lbs_peak = 0;
		for(i=0; i<array.length; i++) {
			if(LBS[i] > lbs) {
				lbs = LBS[i];
				lbs_peak = i;
			}
		}

		int[] lbs_soln = new int[lbs];
		int k = LIS[lbs_peak]-1;
		i = lbs_peak;
		while(LIS[i] != 1) {
			lbs_soln[k--] = array[i];
			i = LIS_soln[i];
		}
		lbs_soln[k] = array[i];
		
		i=lbs_peak;
		k = LIS[lbs_peak] - 1;
		while( LDS[i] != 1) {
			lbs_soln[k++] = array[i];
			i = LDS_soln[i];
		}
		lbs_soln[k] = array[i];
		
		System.out.print("Length of longest bitonic subsequence = " + lbs + " and lbs is ");
		for(i=0; i<lbs;i++) {
			System.out.print(lbs_soln[i]  + " ");
		}
		System.out.println();
	}
	
	public static void Palindrome_Partitioning(char[] str) {
		
		int[][] optimal_solution = new int[str.length][str.length];
		
		int i=0, j=0;
		
		for(i=0; i<str.length; i++) {
			for(j=0; j<=i; j++) {
				optimal_solution[i][j] = 0;
			}
		}
		
		for(i=str.length-1; i>=0; i--) {
			for(j=i+1; j<str.length; j++) {
				if( checkPalindrome(str, i, j) == 1 ) {
					optimal_solution[i][j] = 0;
				}
				else {
					int min_cuts = j-i;
					int k=j-1;
					while( k >= i) {
						if( optimal_solution[i][k] + optimal_solution[k+1][j] < min_cuts ) {
							min_cuts = optimal_solution[i][k] + optimal_solution[k+1][j];
						}
						k -= 1;
					}
					optimal_solution[i][j] = min_cuts + 1;
				}
			}
		}
		
		System.out.println("Minimum cuts required = " + optimal_solution[0][str.length-1]);
	}
	
	public static int checkPalindrome(char[] str, int start, int end) {
		int i=start, j=end;
		
		while(i <= j) {
			if(str[i] != str[j]) {
				return 0;
			}
			i += 1;
			j -= 1;
		}
		return 1;
	}
	
	public static void Partition_Problem(int[] set) {
		
		int sum = 0;
		int i=0, j=0;
		
		for(i=0; i<set.length; i++) {
			sum += set[i];
		}
		
		if(sum % 2 != 0) {
			System.out.println("Set cannot be partitioned since it has odd length");
			return;
		}
		
		sum /= 2;
		boolean[][] optimal_solution = new boolean[set.length][sum+1];
		
		for(i=0; i<set.length; i++) {
			optimal_solution[i][set[i]] = true;
			optimal_solution[i][0] = true;
		}
		
		for(j=1; j<=sum; j++) {
			if(set[0] != j) {
				optimal_solution[0][j] = false;
			}
		}
		
		for(i=1; i<set.length; i++) {
			for(j=1; j<=sum; j++) {
				Boolean case1 = false;
				Boolean case2 = false;
				
				if( set[i] <= j ) {
					case1 = optimal_solution[i-1][j-set[i]];
				}
				else {
					case2 = optimal_solution[i-1][j];
				}
				
				if(case1 == true || case2 == true) {
					optimal_solution[i][j] = true;
				}
				else {
					optimal_solution[i][j] = false;
				}
			}
		}
		
		System.out.println("Partitioning of set = " + optimal_solution[set.length-1][sum]);
	}
	
	public static void longestPalindromicSubstring(String str) {
		int[][] optimal_solution = new int[str.length()][str.length()];
		
		int i=0, j=0;
		
		for(i=0; i<str.length(); i++) {
			for(j=0; j<i; j++) {
				optimal_solution[i][j] = 0;
			}
			optimal_solution[i][j] = 1;
		}
		
		for(i=str.length()-1; i>=0; i--) {
			for(j=i+1; j<str.length(); j++) {
				if( checkPalindrome(str.toCharArray(), i, j) == 1) {
					optimal_solution[i][j] = j-i+1;
				}
				else {
					optimal_solution[i][j] = Math.max(optimal_solution[i+1][j], optimal_solution[i][j-1]);
				}	
			}
		}
		
		System.out.println("Length of longest palindromic substring = " + optimal_solution[0][str.length()-1]);
	}
	
	public static void OptimalBinarySearchTree(int[] nodes, int[] probabilities) {
		int[][] optimal_search_tree = new int[nodes.length][nodes.length];
		
		int i=0, j=0, k=0;
		
		for(i=0; i<nodes.length; i++) {
			for(j=0; j<i; j++) {
				optimal_search_tree[i][j] = 0;
			}
			optimal_search_tree[i][j] = probabilities[i];
		}
		
		for(i=nodes.length-1; i>=0; i--) {
			for(j=i+1;j<nodes.length; j++) {
				int p_sum = 0, min_cost = Integer.MAX_VALUE;
				for(k=i; k<=j; k++) {
					p_sum += probabilities[k];
				}
				for(k=i; k<=j; k++) {
					if(k==i) {
						if( optimal_search_tree[k+1][j] < min_cost) {
							min_cost = optimal_search_tree[k+1][j];
						}
					}
					else if( k==j ) {
						if( optimal_search_tree[i][k-1] < min_cost) {
							min_cost = optimal_search_tree[i][k-1];
						}
					}
					else {
						if(optimal_search_tree[i][k-1] + optimal_search_tree[k+1][j] < min_cost) {
							min_cost = optimal_search_tree[i][k-1] + optimal_search_tree[k+1][j];
						}
					}
				}
				
				optimal_search_tree[i][j] = min_cost + p_sum;
			}
		}
		
		System.out.println("Optimal binary search tree has average weighted search time = " + optimal_search_tree[0][nodes.length-1]);
	}
	
	public void coinChange(int coins[], int total) {
		int len = coins.length;
		if(len == 0) return;
		
		int[][] optimal_solution = new int[total+1][len];
		
		int i=0,j=0;
		
		for(i=0; i<len; i++) {
			optimal_solution[0][i] = 1;
		}
		
		for(i=1; i<=total; i++) {
			for(j=0; j<len; j++) {
				int case1 = (coins[j] <= i) ? (optimal_solution[i-coins[j]][j]) : 0;
				int case2 = (j >= 1) ? optimal_solution[i][j-1] : 0;
				
				optimal_solution[i][j] = case1 + case2;
			}
		}
		
		System.out.println(optimal_solution[total][len-1]);
	}
	
	public void coinChangeMinCoins(int coins[], int total) {
		int len = coins.length;
		
		int[] optimal_solution = new int[total+1];
		
		int i=0, j=0;
		
		optimal_solution[0] = 0;
		
		for(i=1; i<=total; i++) {
			int min_coins = i;
			for(j=0; j<len; j++) {
				if(coins[j] > i) {
					continue;
				}
				else {
					if(1 + optimal_solution[i-coins[j]] < min_coins) {
						min_coins = 1 + optimal_solution[i-coins[j]];
					}
				}
			}
			optimal_solution[i] = min_coins;
		}
		
		System.out.println("Minimum coins needed for change = " + optimal_solution[total]);
	}
	
	public static void main(String[] args) {
		
		int[] path_graph = {1,4,5,4};
		
		Max_Size_Independent_Set(path_graph);
		
		char[] seq1 = {'G','A','A','T','T','C','A','G','T','T','A'};
		char[] seq2 = {'G','G','A','T','C','G','A'};
		int gap_penalty = 0;
		int mismatch_penalty = 0;
		int match_score = 1;
		
		Sequence_Alignment(seq1, seq2, gap_penalty, mismatch_penalty, match_score);
		
		int[] array = {-2,3,4,-1,-2,1,5,-3};
		Max_Sum_Subarray(array);
		
		Ugly_Numbers(150);
		
		char[] sequence1 = {'A','E','D','F','H','R'};
		char[] sequence2 = {'A','B','C','D','G','H'};
		Longest_Common_Subsequence(sequence1, sequence2);
		
		char[] s1 = {'S','U','N','D','A','Y'};
		char[] s2 = {'S','A','T','U','R','D','A','Y'};
		Edit_Distance(s1, s2);
	
		int[][] cost = {{1,2,3},{4,8,2},{1,5,3}};
		Min_Cost_Path(cost, 2, 2);
		
		Lon_Substr_Without_Repeat("ABDEFGABEF");
		
		int[] jumps = {1,3,5,8,9,2,6,7,6,8,9};
		Min_Jumps(jumps);
		
		Binomial_Coefficient(5, 2);
		
		Longest_Palindromic_Subsequence("BBABCBCAB");
		
		int[] prices = {1,5,8,9,10,17,17,20};
		Rod_Cutting(prices);
		
		int[] sequence = {1,101,2,3,100,4,5};
		Max_Sum_Inc_Subsequence(sequence);
		
		int[] array_lbs1 = {1, 11, 2, 10, 4, 5, 2, 1};
		int[] array_lbs2 = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		Longest_Bitonic_Subsequence(array_lbs2);
		
		char[] str = {'a','b','a','b','b','b','a','b','b','a','b','a','b','a'};
		Palindrome_Partitioning(str);
		
		int[] set = {2, 4, 2,6};
		Partition_Problem(set);
		
		String str1 = "forgeeksskeegfor";
		longestPalindromicSubstring(str1);
	}

}
