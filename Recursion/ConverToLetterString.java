package Recursion;

import java.lang.module.ResolutionException;

public class ConverToLetterString {
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    // chs当前在i位置做决定
    // i之前的位置，如何转化已经做过决定了
    // i之后有多少种转化的结果
    public static int process(char[] str, int i) {
        // 如果已经到最后的位置
        if (i == str.length) { 
            return 1; // 返回1种有效的，也就是之前做的决定
        }
        // 之前做的决定不对，因为让i到了一个无法转换的状态
        if (str[i] == '0') { 
            return 0;  // 代表没有有效的
        }
        if (str[i] == '1') {
            int res = process(str, i + 1); // 一定会有情况一：i自己作为单独的部分，后续有多少种方法
            if (i + 1 < str.length) {  //可能会有情况二
                res += process(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1); // i自己作为单独的部分，后续有多少种方法
            // (i和i+1)作为单独的部分并且没有超过26，后续有多少种方法
            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                res += process(str, i + 2);
            }
            return res;
        }
        // chs[i] == '3' ~ '9'，只有一种决定，整个方法结束
        return process(str, i + 1);
    }
}
