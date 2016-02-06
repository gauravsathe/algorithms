package Sorting;

public class Search {

	private static int[] arr;
	
	public Search(int[] a) {
		arr = a;
	}
	
	public void setArr(int[] newArr) {
		arr = newArr;
	}
	
	public int linearSearch(int key) {
		for(int i = 0; i < arr.length; i++) {
			if( arr[i] == key ) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static int binarySearch(int[] nums, int key) {
		int p = 0, q=nums.length-1;
		
		while(p <= q) {
			int mid = (p + q)/2;
			
			if( nums[mid] == key ) {
				return mid;
			}
			else if( nums[mid] < key ) {
				p = mid+1;
			}
			else {
				q = mid-1;
			}
		}
		
		return -1;
	}
	
	public int getLeftPosition(int key, int p, int q) {
		
		if(p < q) {
			int mid = (p+q)/2;
			
			if(arr[mid] == key) {
				if(mid == 0 || arr[mid-1] < arr[mid]) return mid;
				else return getLeftPosition(key, p, mid-1);
			}
			else if( arr[mid] < key ) {
				return getLeftPosition(key, mid+1, q);
			}
			else {
				return getLeftPosition(key, p, mid-1);
			}
		}
		return p;
	}
	
	public int getRightPosition(int key, int p, int q) {
		
		if(p < q) {
			int mid = (p+q)/2;
			
			if(arr[mid] == key) {
				if(mid == arr.length-1 || arr[mid+1] > arr[mid]) return mid;
				else return getRightPosition(key, mid+1, q);
			}
			else if( arr[mid] < key ) {
				return getRightPosition(key, mid+1, q);
			}
			else {
				return getRightPosition(key, p, mid-1);
			}
		}
		return p;
	}
	
	public int countOccurences(int key, int p, int q) {
		
		int leftIndex = getLeftPosition(key, p, q);
		int rightIndex = getRightPosition(key, p, q);
		
		return rightIndex - leftIndex + 1;
	}
	
	public int getMinRotatedArray(int p, int q) {
		
		if(p < q) {
			int mid = (p+q)/2;
			
			if( arr[mid+1] <  arr[mid] ) {
				return arr[mid+1];
			}
			else if( arr[mid] < arr[p] ) {
				return getMinRotatedArray(p, mid-1);
			}
			else {
				return getMinRotatedArray(mid+1, q);
			}
		}
		return p;
	}
	
	public int getPeakElement(int p, int q) {
		if(p < q) {
			int mid = (p+q)/2;
			
			if( mid == 0 && arr[mid+1] < arr[mid] ) return arr[mid];
			if( mid == arr.length-1 && arr[mid-1] < arr[mid]) return arr[mid];
			
			if(arr[mid-1] < arr[mid] && arr[mid+1] < arr[mid]) return arr[mid];
			else if( arr[mid-1] < arr[mid] ) return getPeakElement(mid+1, q);
			else return getPeakElement(p, mid-1);
		}
		
		return -1;
	}
	
	public static int medianOfSortedArrays(int[] arr1, int[] arr2, int p1, int q1, int p2, int q2, int median) {
		
		if(p1 == q1) return arr2[q2];
		if(p2 == q2) return arr1[q1];
		
		int mid1 = (p1+q1)/2;
		int mid2 = (p2+q2)/2;
		
		if( arr1[mid1] < arr2[mid2] ) {
			if( median - (q2-mid2) == 0) {
				return arr1[q1];
			}
			else {
				return medianOfSortedArrays(arr1, arr2, p1, q1, p2, mid2, median-(q2-mid2));
			}
		}
		else {
			if( median - (q1-mid1) == 0) {
				return arr2[q2];
			}
			else {
				return medianOfSortedArrays(arr1, arr2, p1, mid1, p2, q2, median-(q1-mid1));
			}
		}
	}
	
	public static void main(String[] args) {
		
		int[] a = {2,4,1,5,4,8,6,7};
		
		Search newSearch = new Search(a);
		
		//System.out.println(newSearch.binarySearch(8,0,a.length-1));
		
		int [] b = {1,2,3,3,3,4,5,5,6,7,8};
		newSearch.setArr(b);
		
		System.out.println(newSearch.countOccurences(3, 0, b.length-1));
		
		int[] c = {1,2,3,4,5,6,7,8,9};
		newSearch.setArr(c);
		System.out.println(newSearch.getMinRotatedArray(0, c.length-1));
		
		int[] d = {10, 20, 15, 2, 23, 90, 67};
		newSearch.setArr(d);
		System.out.println(newSearch.getPeakElement(0, d.length-1));
		
		int[] arr1 = {1, 2,3,4,5};
	    int[] arr2 = {6,7,8,9,10};
		System.out.println(Search.medianOfSortedArrays(arr1, arr2, 0, arr1.length-1, 0, arr2.length-1, arr1.length));
		
		int [] array = {1,3,1,1,1};
		System.out.println(search(array, 3));
		
		int[] array_1 = {12, 16, 22, 27, 27, 30, 30, 33, 35, 35, 39, 39, 42, 45, 48, 50, 53, 55, 56};
		int[] kNN = kNearestNeighbors(array_1, 35, 7);
		for(int i=0; i<kNN.length; i++) System.out.print(kNN[i] + " ");
	}
	
	public static boolean search(int[] nums, int target) {
        int len = nums.length;
        
        if(len == 0) {
            return false;
        }
        
        int start = 0, end = len-1, mid = 0;
        
        while(start <= end ) {
            mid = (start+end)/2;
            
            if(nums[mid] == target) {
                return true;
            }
            System.out.println(start + " " + end + " " + mid);
            
            if(nums[mid] >= nums[start]) {
                if(target < nums[start]) {
                    start = mid+1;
                }
                else {
                    if(target > nums[mid]) {
                        start = mid+1;
                    }
                    else {
                        end = mid-1;
                    }
                }
            }
            else {
                if(target >= nums[start]) {
                    end = mid - 1;
                }
                else {
                    if(target > nums[mid]) {
                        start = mid + 1;
                    }
                    else {
                        end = mid - 1;
                    }
                }
            }
            //System.out.println(start + " " + end + " " + mid);
        }
        
        
        return false;   
    }
	
	public static int[] kNearestNeighbors(int[] nums, int x, int k) {
		int[] kNN = new int[k];
		
		int index = binarySearch(nums, x);
		
		if(index == -1) {
			return kNN;
		}
		
		int i = index-1, j=index +1;
		int kNN_i = 0;
		
		while(kNN_i < k && i >= 0 && j < nums.length) {
			if(nums[i] == x || nums[i] == nums[i+1]) {
				i -= 1;
				continue;
			}
			
			if(nums[j] == x || nums[j] == nums[j-1]) {
				j += 1;
				continue;
			}
			
			if(Math.abs(nums[j]-x) < Math.abs(nums[i]-x)) {
				kNN[kNN_i] = nums[j];
				j += 1;
				kNN_i += 1;
			}
			else {
				kNN[kNN_i] = nums[i];
				i -= 1;
				kNN_i += 1;
			}
		}
		
		if(kNN_i == k) {
			return kNN;
		}
		
		if( i < 0) {
			while(kNN_i < k) {
				if(nums[j] == x || nums[j] == nums[j-1]) {
					j += 1;
					continue;
				}
				else {
					kNN[kNN_i] = nums[j];
					j += 1;
					kNN_i += 1;
				}
			}
		}
		else {
			while(kNN_i < k) {
				if(nums[i] == x || nums[i] == nums[i+1]) {
					i -= 1;
					continue;
				}
				else {
					kNN[kNN_i] = nums[i];
					i -= 1;
					kNN_i += 1;
				}
			}
		}
		
		return kNN;
	}
}
