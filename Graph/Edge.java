package Graph;

public class Edge {
    public int weight;  // 距离
    public Node from;  // 出发点
    public Node to;  // 目标点

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
