package TrieTree;
import java.util.Arrays;

// 返回字典序
public class LowestLexicography {
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            // 前面的参数字典序低返回负数，后面的参数字典序低返回正数
            // 贪心策略是用(a + b)去比较(b + a)
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 按照贪心策略去把整个数组排序
        Arrays.sort(strs, new MyComparator());
        // 然后依次拼接起来
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs1 = {"jibw", "ji", "jp", 'bw', 'jibw'};
        System.out.println(lowestString(strs1));
    }
}
