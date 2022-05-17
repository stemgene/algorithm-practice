package TrieTree;
import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {
    public static class Node {
        public int p;  // profit
        public int c;  // cost

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    // cost小根堆比较器
    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    // profit大根堆比较器
    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.p - o2.p;
        }
    }

    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        // 所有项目压入到到被锁池中，cost小根堆
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Node(Profits[i], Capital[i]));
        }
        // 挑项目
        for (int i = 0; i < k; i++) {  // 进行k轮
            // 能力所及的项目，全解锁
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll()); // 把符合条件的项目压入到大根堆中
            }
            // 如果没有项目可以挑了，即大根堆为空，提前返回
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }
}
