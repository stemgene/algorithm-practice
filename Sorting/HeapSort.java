package Sorting;
import java.util.Arrays;

public class HeapSort {
    public static void heapSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        // 步骤一和步骤一的优化这两种方法二选一，优化的更快一些
        // 步骤一：创建max-heap
        for (int i = 0; i < arr.length; i++) { // O(N)
            heapInsert(arr, i);  // O(logN)
        }

        // 步骤一的优化：初使时给定整个数组，并已经排成完整二叉树，可以直接heapify
        // 这一步骤的时间复杂度是O(N)
        // for (int i = arr.length - 1; i >= 0; i--) {  //从右往左
        //     heapify(arr, i, arr.length);
        // }

        // 步骤二：交换index=0和heap最后一个位置的数
        int heapSize = arr.length;
        swap(arr, 0, --heapSize); 

        // 步骤三：只要heapsize>0，重复heapify的操作。
        while(heapSize > 0) { // O(N)
            heapify(arr, 0, heapSize); // O(logN)
            swap(arr, 0, --heapSize);  //O(1)
        }
    }

    // 某个数现在处在index位置，往上继续移动
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {  // 当前的数如果大于父位置的数
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;  // index来到父节点位置，直到不再比父大，或来到0位置
            // 如果index来到0，(index-1)/2==0，所以也满足停止条件
        }
    }

    // 某个数在index位置，能否往下移动
    // 传进来的参数： index指从哪个位置开始往下做heapify，一般是0，也可以是任何位置
    // 传进来的参数： heapSize管理heap的大小，和arr的长度无关，一旦越界代表下方无子节点
    public static void heapify (int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; //左孩子的下标

        while (left < heapSize) { // 下方还有子节点的时候，等同于判断从i位置出发下方是否有子节点
            // 两个孩子中，谁的值大，把下标给largest
            // left + 1 < leapSize 是如果有右子节点
            // arr[left + 1] > arr[left] 右子节点的值大于左子节点的值
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1: left;
            // 父节点和较大的子节点之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;  //新的左子节点的下标，并继续执行while循环
        }
    }
    
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                // 打印arr1
                // 打印arr2
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Not good");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() -> [0, 1)所有的小数，等概率返回一个
        // Math.random() * N -> [0,N)所有小数，等概率返回一个
        // (int)(Math.random() * N) -> [0, N]所有的整数，等概率返回一个
        
        int[] arr = new int[(int)((maxSize + 1) * Math.random())]; //长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
