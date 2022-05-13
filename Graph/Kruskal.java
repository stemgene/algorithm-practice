package Graph;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Stack;

// undirected graph only
public class Kruskal {

    // 9 - 46 行是老师现场写的，和其余的code不在一个版本中。
    // 集合合并查询结构，没有并查集UnionFind快。并查集可以让24-46行代码的时间复杂度实现常数级
    public static class MySets{
        // 定义一个点以及对应的自己的list
        public HashMap<Node, List<Node>> setMap;
        public MySets(List<Node> nodes) {
            // 初始化时，载入所有点，并设置每个点只存在于自己的集合中
            for (Node cur : nodes) {
                List<Node> set = new ArrayList<Node>();
                // 自己的集合
                set.add(cur);
                setMap.put(cur, set);
            }
        }

        // 提供接口，返回两个点是否在同一个集合中
        public boolean isSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            // 比较内存地址，如果不是一个集合，内存地址不一样
            return fromSet == toSet; 
        }

        // 把from点所在的集合和to点所在的集合合并为同一个集合
        public void union(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);

            // 首先把from和to集合拿出来
            // 然后把to集合中所有node全部加入到from中去
            for (Node toNode : toSet) {
                fromSet.add(toNode);
                // 每一个to节点把list指向from
                setMap.put(toNode, fromSet);
                //此时所有to的点都在from中，内存地址也都变成一个了
            }
        }
    }


    // Union-Find Set
    public static class UnionFind {
        // key 某一个节点，value key节点往上的节点
        private HashMap<Node, Node> fatherMap;
        // key 某一个集合的代表节点，value key所在集合的节点个数
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findFather(Node n) {
            Stack<Node> path = new Stack<>();
            while(n != fatherMap.get(n)) {
                path.add(n);
                n = fatherMap.get(n);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), n);
            }
            return n;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if (aDai != bDai) {
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSetSize + bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSetSize + bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind(); // 生成并查集结构
        unionFind.makeSets(graph.nodes.values());  // 初始化并查集
        // 创建heap，按照edge的weight从小到大依次弹出
        // 定义比较器
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {  // M条边
            priorityQueue.add(edge);  // O(logM)
        }
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {  // M条边
            Edge edge = priorityQueue.poll();  // O(logM) 每条边依次弹出
            if (!unionFind.isSameSet(edge.from, edge.to)) { // O(1)，判断是否相同
                result.add(edge);
                unionFind.union(edge.from, edge.to);  // 合并数集
            }
        }
        return result;
    }
}
