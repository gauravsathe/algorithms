package Sorting;

public class Basic_Sort {

	public static void main(String[] args) {
		int[] A = {5,2,6,1,8,3,4,7};
		BubbleSort(A);
		printArray(A);
		
		int[] B = {2,8,6,1,5,3,4,7};
		SelectionSort(B);
		printArray(B);
		
		int[] C = {8,4,6,2,7,5,3,1};
		InsertionSort(C);
		printArray(C);
	}
	
	public static void BubbleSort(int[] array) {
		int i=0, j=0;
		int swaps = 0;
		
		for(i=array.length-1;i>0; i--) {
			swaps = 0;
			for(j=0; j<i; j++) {
				if(array[j] > array[j+1]) {
					swapElements(j, j+1, array);
					swaps += 1;
				}
			}
			if(swaps == 0) {
				break;
			}
		}
	}
	
	public static void SelectionSort(int[] array) {
		int i=0, j=0;
		for(i=0;i<array.length;i++) {
			for(j=i+1; j<array.length;j++) {
				if(array[i] > array[j]) {
					swapElements(i, j, array);
				}
			}
		}
	}
	
	public static void InsertionSort(int[] array) {
		int i=0, j=0;
		int key = 0;
		
		for(i=1;i<array.length; i++) {
			j=i-1;
			key = array[i];
			while( j >= 0 && key < array[j] ) {
				swapElements(j, j+1, array);
				j -= 1;
			}
			array[j+1] = key;
		}
	}
	
 	public static void swapElements(int x, int y, int[] arr) {
		int temp;
		
		temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	public static void printArray(int[] arr) {
		int i=0;
		for(i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
