package Recursion;

public class CardsInLine {
    // 常规解法
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 玩家A：f(arr, 0, arr.length - 1)。在L～R的范围内先手
        // 玩家B：s(arr, 0, arr.length - 1)。在L～R的范围内后手
        // 玩家A和玩家B谁大谁获胜
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    public static int f(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        // 情况一：arr[i] + s(arr, i + 1, j)。如果拿走i，剩下的范围为后手
        // 情况二：arr[j] + s(arr, i, j - 1)。如果拿走j，剩下的范围为后手
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    public static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        // 情况一：f(arr, i + 1, j)。如果对方拿走i，剩下的范围为先手
        // 情况二：f(arr, i, j - 1)。如果对方拿走j，剩下的范围为先手
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }

    // 动态规划
    public static int win2(int[] arr) {
        if (arr == null || arr.length ==0) {
            return 0;
        }

        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 1};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }
}
