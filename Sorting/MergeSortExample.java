package Sorting;
import java.util.Arrays;

public class MergeSortExample {
    public static void mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        System.out.println("L = " + L + ", mid = " + mid + ", R = " + R);
        mergeSort(arr, L, mid);
        mergeSort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }
    
    public static void merge(int[] arr, int L, int mid, int R) {
        System.out.println("Array is: " + Arrays.toString(arr));
        int[] tempArr = new int[R - L + 1];
        System.out.println("The tempArr is " + Arrays.toString(tempArr));
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while(p1 <= mid && p2 <= R) {
            //tempArr[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
            if (arr[p1] <= arr[p2]) {
                tempArr[i] = arr[p1];
                i += 1;
                p1 += 1;
            } else {
                tempArr[i] = arr[p2];
                i += 1;
                p2 += 1;
            }
            System.out.println("Before out of bound: " + Arrays.toString(tempArr));
        }
        while(p1 <= mid) {
            tempArr[i++] = arr[p1++];
        }
        while(p2 <= R) {
            tempArr[i++] = arr[p2++];
        }
        System.out.println("After out of bound: " + Arrays.toString(tempArr));
        for (int j = 0; j < tempArr.length; j++) {
            arr[L + j] = tempArr[j];
        }
        System.out.println("After copy: " + Arrays.toString(arr));
    }
    
    public static void main(String[] args) {
        int[] arr = {4,2,5,3,10,4,6,245,7,1,34,2,45,12};
        //int[] arr = {5,3,4};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("The sorted array is: ");
        System.out.println(Arrays.toString(arr));
    }
}

// The result is:
/*
L = 0, mid = 6, R = 13
L = 0, mid = 3, R = 6
L = 0, mid = 1, R = 3
L = 0, mid = 0, R = 1
Array is: [4, 2, 5, 3, 10, 4, 6, 245, 7, 1, 34, 2, 45, 12]
The tempArr is [0, 0]
Before out of bound: [2, 0]
After out of bound: [2, 4]
After copy: [2, 4, 5, 3, 10, 4, 6, 245, 7, 1, 34, 2, 45, 12]
L = 2, mid = 2, R = 3
Array is: [2, 4, 5, 3, 10, 4, 6, 245, 7, 1, 34, 2, 45, 12]
The tempArr is [0, 0]
Before out of bound: [3, 0]
After out of bound: [3, 5]
After copy: [2, 4, 3, 5, 10, 4, 6, 245, 7, 1, 34, 2, 45, 12]
Array is: [2, 4, 3, 5, 10, 4, 6, 245, 7, 1, 34, 2, 45, 12]
The tempArr is [0, 0, 0, 0]
Before out of bound: [2, 0, 0, 0]
Before out of bound: [2, 3, 0, 0]
Before out of bound: [2, 3, 4, 0]
After out of bound: [2, 3, 4, 5]
After copy: [2, 3, 4, 5, 10, 4, 6, 245, 7, 1, 34, 2, 45, 12]
L = 4, mid = 5, R = 6
L = 4, mid = 4, R = 5
Array is: [2, 3, 4, 5, 10, 4, 6, 245, 7, 1, 34, 2, 45, 12]
The tempArr is [0, 0]
Before out of bound: [4, 0]
After out of bound: [4, 10]
After copy: [2, 3, 4, 5, 4, 10, 6, 245, 7, 1, 34, 2, 45, 12]
Array is: [2, 3, 4, 5, 4, 10, 6, 245, 7, 1, 34, 2, 45, 12]
The tempArr is [0, 0, 0]
Before out of bound: [4, 0, 0]
Before out of bound: [4, 6, 0]
After out of bound: [4, 6, 10]
After copy: [2, 3, 4, 5, 4, 6, 10, 245, 7, 1, 34, 2, 45, 12]
Array is: [2, 3, 4, 5, 4, 6, 10, 245, 7, 1, 34, 2, 45, 12]
The tempArr is [0, 0, 0, 0, 0, 0, 0]
Before out of bound: [2, 0, 0, 0, 0, 0, 0]
Before out of bound: [2, 3, 0, 0, 0, 0, 0]
Before out of bound: [2, 3, 4, 0, 0, 0, 0]
Before out of bound: [2, 3, 4, 4, 0, 0, 0]
Before out of bound: [2, 3, 4, 4, 5, 0, 0]
After out of bound: [2, 3, 4, 4, 5, 6, 10]
After copy: [2, 3, 4, 4, 5, 6, 10, 245, 7, 1, 34, 2, 45, 12]
L = 7, mid = 10, R = 13
L = 7, mid = 8, R = 10
L = 7, mid = 7, R = 8
Array is: [2, 3, 4, 4, 5, 6, 10, 245, 7, 1, 34, 2, 45, 12]
The tempArr is [0, 0]
Before out of bound: [7, 0]
After out of bound: [7, 245]
After copy: [2, 3, 4, 4, 5, 6, 10, 7, 245, 1, 34, 2, 45, 12]
L = 9, mid = 9, R = 10
Array is: [2, 3, 4, 4, 5, 6, 10, 7, 245, 1, 34, 2, 45, 12]
The tempArr is [0, 0]
Before out of bound: [1, 0]
After out of bound: [1, 34]
After copy: [2, 3, 4, 4, 5, 6, 10, 7, 245, 1, 34, 2, 45, 12]
Array is: [2, 3, 4, 4, 5, 6, 10, 7, 245, 1, 34, 2, 45, 12]
The tempArr is [0, 0, 0, 0]
Before out of bound: [1, 0, 0, 0]
Before out of bound: [1, 7, 0, 0]
Before out of bound: [1, 7, 34, 0]
After out of bound: [1, 7, 34, 245]
After copy: [2, 3, 4, 4, 5, 6, 10, 1, 7, 34, 245, 2, 45, 12]
L = 11, mid = 12, R = 13
L = 11, mid = 11, R = 12
Array is: [2, 3, 4, 4, 5, 6, 10, 1, 7, 34, 245, 2, 45, 12]
The tempArr is [0, 0]
Before out of bound: [2, 0]
After out of bound: [2, 45]
After copy: [2, 3, 4, 4, 5, 6, 10, 1, 7, 34, 245, 2, 45, 12]
Array is: [2, 3, 4, 4, 5, 6, 10, 1, 7, 34, 245, 2, 45, 12]
The tempArr is [0, 0, 0]
Before out of bound: [2, 0, 0]
Before out of bound: [2, 12, 0]
After out of bound: [2, 12, 45]
After copy: [2, 3, 4, 4, 5, 6, 10, 1, 7, 34, 245, 2, 12, 45]
Array is: [2, 3, 4, 4, 5, 6, 10, 1, 7, 34, 245, 2, 12, 45]
The tempArr is [0, 0, 0, 0, 0, 0, 0]
Before out of bound: [1, 0, 0, 0, 0, 0, 0]
Before out of bound: [1, 2, 0, 0, 0, 0, 0]
Before out of bound: [1, 2, 7, 0, 0, 0, 0]
Before out of bound: [1, 2, 7, 12, 0, 0, 0]
Before out of bound: [1, 2, 7, 12, 34, 0, 0]
Before out of bound: [1, 2, 7, 12, 34, 45, 0]
After out of bound: [1, 2, 7, 12, 34, 45, 245]
After copy: [2, 3, 4, 4, 5, 6, 10, 1, 2, 7, 12, 34, 45, 245]
Array is: [2, 3, 4, 4, 5, 6, 10, 1, 2, 7, 12, 34, 45, 245]
The tempArr is [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
Before out of bound: [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
Before out of bound: [1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
Before out of bound: [1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
Before out of bound: [1, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
Before out of bound: [1, 2, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0]
Before out of bound: [1, 2, 2, 3, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0]
Before out of bound: [1, 2, 2, 3, 4, 4, 5, 0, 0, 0, 0, 0, 0, 0]
Before out of bound: [1, 2, 2, 3, 4, 4, 5, 6, 0, 0, 0, 0, 0, 0]
Before out of bound: [1, 2, 2, 3, 4, 4, 5, 6, 7, 0, 0, 0, 0, 0]
Before out of bound: [1, 2, 2, 3, 4, 4, 5, 6, 7, 10, 0, 0, 0, 0]
After out of bound: [1, 2, 2, 3, 4, 4, 5, 6, 7, 10, 12, 34, 45, 245]
After copy: [1, 2, 2, 3, 4, 4, 5, 6, 7, 10, 12, 34, 45, 245]
The sorted array is: 
[1, 2, 2, 3, 4, 4, 5, 6, 7, 10, 12, 34, 45, 245]
*/
