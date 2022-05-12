package Graph;


public class GraphGenerator {
    // matrix 所有的边
    // N * 3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    // 输入参数：matrix[0][0]代表出发from点,matrix[0][1]代表目标to点,matrix[0][2]代表距离权重
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer weight = matrix[i][2];
            // 如果该点不在HashMap中，将其存入
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            // 创建新的Edge
            Edge newEdge = new Edge(weight, fromNode, toNode);
            // 在from点的邻居里，把to点加上
            fromNode.nexts.add(toNode);
            // from的出度++
            fromNode.out++;
            // to的入度++
            toNode.in++;
            // 把新建的edge加入到from点的edges属性中
            fromNode.edges.add(newEdge);
            // 把新建的edge放入到edges中
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
