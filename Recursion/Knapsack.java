package Recursion;

public class Knapsack {
    public static int maxValue1(int[] weights, int[] values, int bag) {
        return process1(weights, values, 0, 0, bag);
    }

    // i以后的货物自由选择，形成的最大价值返回
    // alreadyweight: 之前做的决定已经有的重量
    // bag：不能超过bag的重量
    public static int process1(int[] weights, int[] values, int i, int alreadyweight, int bag) {
        if (alreadyweight > bag) {
            return 0;
        }
        if (i == weights.length) {
            return 0;
        }
        return Math.max(
            // 如果决定不要i号货，获得不了i号货的价值，重量也不会有增加，然后让i+1去做后续选择
            process1(weights, values, i + 1, alreadyweight, bag),
            // 如果决定要i号货，不仅可以获得i号货的价值，还要加上already的重量，然后让i+1去做后续选择
            values[i] + process1(weights, values, i + 1, alreadyweight + weights[i], bag)
        );
    }

    // 这个方法比process2多了一个alreadyvalue参数，但评论说是正确的
    public static int process2(int[] weights, int[] values, 
                        int i, int alreadyweight, int alreadyValue, int bag) {
        // 超重
        if (alreadyweight > bag) {
            return 0;
        }
        // 到最后
        if (i== values.length) {
            return alreadyValue;
        }
        return Math.max(
            // 选择一：不要第i号货，重量和价值都不增加
            process2(weights, values, i + 1, alreadyweight, alreadyValue, bag),
            // 选择二：要第i号货，重量和价值都增加
            process2(weights, values, i + 1, alreadyweight + weights[i], alreadyValue + values[i], bag)
        );

    }

    public static int maxValue2(int[] c, int[] p, int bag) {
        int[][] dp = new int[c.length + 1][bag + 1];
        for (int i = c.length - 1; i >= 0; i--) {
            for (int j = bag; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + c[i] <= bag) {
                    dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
                }
            }
        }
        return dp[0][0];
    }
}
