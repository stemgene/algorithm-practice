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

    /*
    以下是改进后的优化算法：自定义min-heap
    */
    public static class NodeRecord {
        // 内容只有node和距离记录
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] nodes; // 堆的底层结构就是数组
        // value：任何一个节点对应的index值。用来更新原有节点的值
        private HashMap<Node, Integer> heapIndexMap; // 可以通过index查到node在哪个位置上。
        // value：节点对应head的最短距离
        private HashMap<Node, Integer> distanceMap;
        // 目前这个堆上一共有多少节点
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 定义addOrUpdateOrIgnore方法
        // add：如果有一个节点的记录是第一次建立
        // update：之前有记录，用更小的值替换
        // Ignore：之前有记录，并且更新的数据比当前记录不大，就忽略
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) { // 如果node在堆上
                // 之前的距离和现在的距离哪个小，更新哪个
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                // 向上冒
                insertHeapify(node, heapIndexMap.get(node));
            }
            // 如果一个节点没有进过堆，新建记录
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
            // 对于进过堆，但目前不在堆上的，ignore
        }

        // 定义pop方法
        public NodeRecord pop() {
            // 生成record：堆顶元素，距离
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            // 用最后一个元素替换堆顶
            swap(0, size - 1);
            // 此时原本的头节点在size-1位置
            heapIndexMap.put(nodes[size - 1], -1);
            // 删掉此节点
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            // 新的头节点heapify
            heapify(0, --size);
            return nodeRecord;
        }

        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                // 第136-138由于代码行太长，后半段没有完全copy教程，需要参考heapsort.java中的heapify过程去理解自行填写
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left + 1])  //小于号后面的需要自己写
                        ? left + 1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]); // 小于号后面的需要自己写
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        // node是否进来过min-heap
        // 有两种情况：一是目前正在堆中，二是曾经在堆中，但已经被pop走了，此时返回的结果是-1
        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        // node当前是否在堆中
        private boolean inHeap(Node node) {
            // 进来过 && 当前位置不是-1
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        // 在堆中调换两个节点
        // 既要在Node数组中调，在heapIndexMap中的相应位置也要调
        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0); // 初始的head到head节点的距离是0
        // 返回哈希表，key是每一个node，value是距离
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop(); // 弹出后，这一条记录以后就不需要了
            Node cur = record.node;
            int distance = record.distance;
            // 弹出节点的后续节点的距离都需要算一遍
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }
}
