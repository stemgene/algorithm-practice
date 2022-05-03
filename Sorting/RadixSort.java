package Sorting;
import java.util.Arrays;

public class RadixSort {
    //only for non-negative value
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        //通过遍历找到最大值
        for (int i = 0; i < arr.length; i++) { 
            max = Math.max(max, arr[i]);
        }
        int res = 0;  // 最大值的位数
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    // arr[begin...end]排序
    // 参数digit表示最大值有几个10进制的位数
    public static void radixSort(int[] arr, int L, int R, int digit) { 
        final int radix = 10;  // 以10为基底
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] bucket = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) { //有多少位就进出几次
            //System.out.println("The digit = " + d);
            /**
             * 10个空间
             * count[0] 当前位（d位）是0的数字有多少个
             * count[1] 当前位（d位）是(0, 1)的数字有多少个
             * count[2] 当前位（d位）是(0, 1, 2)的数字有多少个
             * count[i] 当前位（d位）是(0 ~ i)的数字有多少个
             */
            int[] count = new int[radix];  //count[0...9]
            // 统计count的词频
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);  // 如果d=1，取个位数；如果d=3，取百位数     
                count[j]++;  // 词频统计
            }
            // 把count处理成前缀和数组
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 入桶：原数组从右往左遍历，把原数组的值放入桶中
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];  // 把原数组的值放入到bucket指定位置
                count[j]--;  // 词频--
            }
            // 出桶
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = bucket[j];
            }
        }

    }

    public static int getDigit(int x, int d) {
        //System.out.println("X = " + x);
        return ((x / ((int)Math.pow(10, d - 1))) % 10);
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
            radixSort(arr1);
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
        radixSort(arr);
        printArray(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() -> [0, 1)所有的小数，等概率返回一个
        // Math.random() * N -> [0,N)所有小数，等概率返回一个
        // (int)(Math.random() * N) -> [0, N]所有的整数，等概率返回一个
        
        int[] arr = new int[(int)((maxSize + 1) * Math.random())]; //长度随机
        for (int i = 0; i < arr.length; i++) {
            int temp = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
            // Make sure the array is non-negative
            arr[i] = temp > 0 ? temp: -temp;
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
