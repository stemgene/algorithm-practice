public class Recursion_GetMax {
    public static void main(String[] args) {
        int[] arr = {0, 9,2,3,4,5,6,7};
        int arrMax = process(arr, 0, arr.length-1);
        System.out.println(arrMax);
    }

    // arr[L...R]范围上求最大值
    public static int process(int[] arr, int L, int R) {
        if (L == R) { // arr[L...R]范围上只有一个数，直接返回， base case
            return arr[L];
        }
        int mid = L + ((R - L) >> 1); // 中点
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }
}
