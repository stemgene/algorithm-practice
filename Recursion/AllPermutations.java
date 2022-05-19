package Recursion;

import java.util.ArrayList;

public class AllPermutations {
    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();
        process(chs, 0, res);
        res.sort(null); // sort做不做都可以
        return res;
    }

    // str[i...]，i之后所有的字符，都可以在i位置上，后续都去尝试
    // str[0...i-1]范围上，是之前做的选择
    // 请把所有的字符串形成的全排列，加入到res里
    // res：返回的结果，所有的排列都收集到这里
    // 第二个要求用到的方法是分枝限界，即剪枝。这种方法比得到所有结果，然后删重复数据的方法要快。
    // 两个方法的理论时间复杂度是一样的，但实际过程中有可能没有重复的而不需要剪枝，所以常数项的时间更少
    public static void process(char[] str, int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }
        boolean[] visit = new boolean[26];  // 第二个要求：没有重复组合。表示某一个字符之前试没试过。vist[0]代表试没试过a。。。
        for (int j = i; j < str.length; j++) {  // j从i出发，代表i之后所有的位置
            if (!visit[str[j] - 'a']) { // 第二个要求：没有重复组合。这个字符只有之前没有试过，才执行交换的过程
                visit[str[j] - 'a'] = true;  // 第二个要求：没有重复组合。标记试过
                swap(str, i, j);  // 都可以来到i位置
                process(str, i + 1, res);  // 在str中直接改，节省空间，运行分支
                swap(str, i, j); // 交换回来，不还原的话，原字符串的顺序就被更改了
            }            
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}
