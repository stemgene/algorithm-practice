import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // arr[1...R]排好序
    public static void process(int[] arr, int L, int R) {
        if(L < R) {
            // 随机选择一个数，和最右边的数做交换
            swap(arr, L + (int)(Math.random() * (R - L + 1)), R);
            // partition返回的数组的长度为2，代表“等于区域”的左边界p[0]和右边界p[1]
            int[] p = partition(arr, L, R);
            process(arr, L, p[0] - 1);  // p[0] - 1是“小于区域”的右边界
            process(arr, p[1] + 1, R);  // p[1] + 1是“大于区域”的左边界
        }
    }

    // 处理arr[1...R]的函数
    // 默认以arr[r]做划分，arr[r] -> p   <p    ==p    >p
    // 返回等于区域（左边界，右边界），所以返回一个长度为2的数组res，res[0], res[1]
    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1; // <区右边界
        int more = R; // >区左边界
        while (L < more) { // L表示当前数的位置 arr[R]  -> 划分值
            if (arr[L] < arr[R]) { // 当前数 < 划分值
                swap(arr, ++less, L++);
            }else if (arr[L] > arr[R]) { // 当前数 > 划分值
                swap(arr, --more, L);
            }else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[] {less + 1, more};
    }

    public static void swap(int[] arr, int L, int R) {
        int temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;
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
            quickSort(arr1);
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
        quickSort(arr);
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
