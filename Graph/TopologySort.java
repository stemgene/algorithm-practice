package Graph;
import java.util.ArrayList;
import java.util.Queue;

public class TopologySort {
    // directed graph and no loop
    public static List<Node> sortedTopology(Graph graph) {
        // key: 某一个node
        // value: 剩余的入度
        HashMap<Node, Iteger> inMap = new HashMap<>();
        // 入度为0的点，才能进这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        // 先把所有的点遍历一遍，把所有点及其入度存入到inMap中，并把入度为0的点压入queue中
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);  // node.in是入度
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        // 拓扑排序的结果，依次加入result
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();  // poll入度为0的点
            result.add(cur); // 加入到拓扑排序的结果中
            for (Node next : cur.nexts) {
                // 把cur节点的next的入度 - 1
                inMap.put(next, inMap.get(next) - 1);
                // 如果再发现有入度为0的，压入queue
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
