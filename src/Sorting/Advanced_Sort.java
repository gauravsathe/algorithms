package Sorting;

import java.io.*;
import java.util.ArrayList;

public class Advanced_Sort {

	public static void main(String[] args) {
		int[] A = {5,2,6,1,8,3,4,7};
		int totalInversions = MergeSort(A, 0, A.length-1);
		printArray(A);
		System.out.println("Inversion in A = " + totalInversions);
		
		int[] B = {5,2,6,1,8,3,4,7};
		QuickSort(B, 0, A.length - 1);
		printArray(B);
		
		int[] C = {5,2,6,1,8,3,4,7};
		System.out.println(RandomizedSelection(C, 0, C.length-1, 4));
		
		ArrayList<Integer> inp = new ArrayList<Integer>();
		try {
			File input = new File("IntegerArray2.txt");
			FileReader fr = new FileReader(input);
			BufferedReader buff = new BufferedReader(fr);
			
			String num;
			while((num = buff.readLine()) != null) {
				inp.add(Integer.parseInt(num));
			}
			
			buff.close();
			
			int[] D = new int[inp.size()];
			for(int i=0; i<inp.size(); i++) {
				D[i] = inp.get(i).intValue();
			}
			for(int i=0; i<20; i++) {
				System.out.println(D[i]);
			}
			
			System.out.println(MergeSort(D, 0, D.length-1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int MergeSort(int[] array, int p, int q) {
		
		int r = 0;
		int inversions = 0;
		int leftInversions = 0, rightInversions = 0, currentInversions = 0;
		
		if( p < q ) {
			r = (p+q)/2;
			leftInversions = MergeSort(array, p, r);
			rightInversions = MergeSort(array, r+1, q);
			currentInversions = MergeAndCountInversions(array, p, q, r);
			
			inversions += leftInversions + rightInversions + currentInversions;
		}
		
		return inversions;
	}
	
	public static int MergeAndCountInversions(int[] arr, int p, int q, int r) {
		int x1 = r-p+1;
		int x2 = q-r;
		int i=0, j=0, k=0;
		
		int[] L1 = new int[x1+1];
		int[] L2 = new int[x2+1];
		
		for(i=0;i<x1;i++) L1[i] = arr[p+i];
		L1[i] = Integer.MAX_VALUE;
		
		for(i=0;i<x2;i++) L2[i] = arr[r+1+i];
		L2[i] = Integer.MAX_VALUE;
		
		i=0;
		j=0;
		int inversions = 0;
		for(k=p;k<=q;k++) {
			if( L1[i] <= L2[j] ) {
				arr[k] = L1[i];
				i += 1;
			}
			else {
				arr[k] = L2[j];
				inversions += x1 - i;
				j += 1;
			}
		}
		return inversions;
	}
	
	public static void printArray(int[] arr) {
		int i=0;
		for(i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void swapElements(int x, int y, int[] arr) {
		int temp;
		
		temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	public static void QuickSort(int[] A, int p, int q) {
		
		int r;
		if( p<q ) {
			r = Partition(A, p, q);
			QuickSort(A, p, r-1);
			QuickSort(A, r+1, q);
		}
	}
	
	public static int Partition(int[] A, int p, int q) {
		int pivot = (int)(Math.random() * (q-p));
		swapElements(p, p+pivot, A);
		pivot = p;
		
		int i=0, j=0;
		
		i = pivot + 1;
		
		for(j=pivot+1;j<=q;j++) {
			if(A[j] < A[pivot]) {
				swapElements(i, j, A);
				i += 1;
			}
		}
		
		swapElements(pivot, i-1, A);
		
		return i-1;
	}
	
	public static int RandomizedSelection(int[] A, int p, int q, int order) {
		
		int r;
		if( p < q ) {
			r = Partition(A, p, q);
			
			if( r+1 == order ) {
				return A[r];
			}
			else if( r+1 > order ) {
				return RandomizedSelection(A, p, r-1, order);
			}
			else {
				return RandomizedSelection(A, r+1, q, order-r);
			}
		}
	
		return A[p];
		
	}

}
