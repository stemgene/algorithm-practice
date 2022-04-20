import java.util.Arrays;

public class SmallSum {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length -1);
    }

    public static int process(int[] arr, int L, int R){
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        // return左侧排序求小和的数量 + 右侧排序求小和的数量 + merge的数量 = 所有小和的数量
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int mid, int R){
        int[] tempArr = new int[R - L + 1];
        int i = 0;  // for tempArr
        int p1 = L;
        int p2 = mid + 1;
        int res = 0;
        while(p1 <= mid && p2 <= R) {  //在没越界的情况下,
            //只有左组比右组小才产生小和增加的行为
            res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0; //R-p2+1: 当前右组有多少数比当前p1所指的数要大
            tempArr[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // p1或p2先越界，执行下面的二选一
        while(p1 <= mid) { // 如p1没越界，把p1剩下的copy到tempArr中去
            tempArr[i++] = arr[p1++];
        }
        while(p2 <= R) { // 如p2没越界，把p2剩下的copy到tempArr中去
            tempArr[i++] = arr[p2++];
        }
        // 最后把tempArr中的元素都覆盖到arr中，完成排序
        for(i = 0; i < tempArr.length; i++) {
            arr[L + i] = tempArr[i];
        }
        return res;
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
            SmallSum(arr1);
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
        SmallSum(arr);
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
