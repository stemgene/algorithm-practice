package Graph;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim {
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> set = new HashSet<>(); // 存放考察过的点，不在此set中的就是新的点
        Set<Edge> result = new HashSet<>();  // 依次挑选的边在result里

        // for循环处理森林孤岛问题，万一有不连通的点，可以确保不落下
        // 如果已知所有点都是联通的，不需要for循环
        for (Node node : graph.nodes.value()) {  // 随机选择一个点
            // node是开始点
            if (!set.contains(node)) {
                set.add(node);
                for (Edge edge : node.edges) {  // 由一个点解锁所有相连的边
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll();  // 弹出解锁的边中最小的边
                    Node toNode = edge.to;  // 检查可能的一个新的点;
                    if (!set.contains(toNode)) {  // 不含有的时候，就是新的点
                        set.add(toNode);
                        result.add(edge);
                        // 最后把to点的所有边放到优先级队列中，虽然此处会出现重复压栈边的情况，但弹出时仍选没有在set中的点对应的边，所以没有影响
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }
}
