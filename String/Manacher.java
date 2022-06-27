package String;

public class Manacher {

    /**
     * 
     * 以下是整个过程的伪代码，用来估计时间复杂度
     * public static int[] manacher(String s) {
     *      // 1221 -> #1#2#2#1
     *      s -> 处理串str
     *      char[] str;
     *      int R = ?;
     *      int C = ?;
     *      
     *      // 回文半径数组，长度是str的长度
     *      int[] pArr = new int[str.length];
     * 
     *      // 从左往右依次求
     *      for(int i = 0; i < str.length; i++) {
     *          if(i在R外部){ // 情况一
     *              从i开始往两边暴力扩；
     *              R增加；
     *          } else { // 情况二
     *              if(i'回文区域在L和R内部){
     *                  pArr[i] = 某个O(1)表达式；
     *              } else if (i'回文区域有一部分在L之外) {
     *                  pArr[i] = 某个O(1)表达式；
     *              } else { // i'回文区域和L的边界压线
     *                  从R的右侧字符开始扩，小加速，然后确定pArr[i]的答案；
     *                  第一步扩失败了，R不变；
     *                  否则，R变大
     *              }
     *          }
     *      }
     * }
     */

    // 将s变成str，用#填充到空隙内
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1]; // 总长度
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);  // 1221 -> #1#2#2#1
        int[] pArr = new int[str.length]; // 回文半径数组
        int C = -1; // 中心
        int R = -1; // 回文右边界再往右一个位置（表示终止位置） 最右的有效区是R-1位置
        int max = Integer.MIN_VALUE; // 扩出来的最大值
        for (int i = 0; i != str.length; i++) {  // 每一个位置都求回文半径
            // 把下述两类大情况合在一起，求出它们都存在的不需要验就知道的区域，i至少的回文区域，先给pArr[i]
            // 情况一 R !> i：i在R外，返回自己的回文 == 1
            // 情况二 R > i：2.1, 2.2和2.3。i在R内。pArr[2 * C - i]是i'的位置。R - i是i到R的距离
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 求出来pArr[i]之后（确定下来不需要验的区域），然后从区域开始往外验，看看R能否变得更大
            // 算法维护：有一些情况不需要往外扩，但也写在这里了，相当于扩一次就失败，从而减少更多的判断。
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                // str[i + pArr[i]]是i右侧到达的字符+至少不用验的位置 == str[i - pArr[i]]左边字符到达位置
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++; //回文半径++，左右扩的更大
                } else {
                    break;
                }
            }
            // 如果回文半径数组pArr[]更往右，更新R和C
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        // max是加上#的回文半径，如#1#2#1#, 回文半径是2#1#为4
        // max - 1是去掉#的原始字符串的最大回文长度（相当于原始字符串的直径，即121）
        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }
}
