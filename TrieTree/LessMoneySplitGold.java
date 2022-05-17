package TrieTree;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LessMoneySplitGold {
    public static int lessMoney(int[] arr) {
        // 把所有数字压入小根堆
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i< arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();  // 弹出两个数字
            sum += cur; // 相加
            pQ.add(cur); // 再压入小根堆
        }
        return sum;
    }

    public static class MinheapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2; // <0 o1 < o2 负数
        }
    }
}
