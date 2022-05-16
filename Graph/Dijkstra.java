package Graph;
import java.util.HashMap;

public class Dijkstra {
    public static HashMap<Node, Integer> dijkstra1(Node head) {
        // 从head出发到所有点的最小距离
        // key：从head出发到达key某一个节点
        // value：从head出发到达key的最小距离，不管中间经过了哪些点，只要是最小的就会被更新
        // 如果在表中，没有T的记录，含义是目前为止从head出发到T这个点的距离为正无穷
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0); // 加入自己的点
        // 锁定记录，已经求过距离的节点，存在selectedNodes中，以后再也不碰
        HashSet<Node> selectedNodes = new HashSet<>();
        // 从distanceMap中找到一个最小的记录来处理，但是这个记录不能是已经锁定的点
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;  // 直接和minNode连接的点
                if (!distanceMap.containsKey(toNode)) {  // 如果没在distanceMap中，则是正无穷
                    distanceMap.put(toNode, distance + edge.weight);  // 更新为distance + weight
                }
                // toNode点之前的距离和distance+weight比较，取更小值
                distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
            }
            selectedNodes.add(minNode);  // 锁定
            // 再从distanceMap中选择一个没选过的最小distance的点
            // 当distanceMap都被选完时，minNode==null，则停止while循环
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    // 从distanceMap中找到一个最小的记录来处理，但是这个记录不能是已经锁定的点
    public static Node getMindistanceAndUnselectedNode(
        HashMap<Node, Integer> distanceMap,
        HashSet<Node> touchedNodes
    ) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        // 处理每一条记录
        for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();  // 得到距离
            // 如果不在锁定集中，且最小，返回该节点
            if (!touchedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
