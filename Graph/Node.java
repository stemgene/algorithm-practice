package Graph;
import java.util.ArrayList;

public class Node {
    public int value;  // 点上的值，也可以是String类型
    public int in;  // 一个点的入度，有多少其他点发过来的路径指向该点。对于无向图来说，入度和出度一致
    public int out;  // 出度，概念同上
    public ArrayList<Node> nexts;  // 可以由该点出发到达的邻点，有向时只算从该点出发的，而不算被动指向该点的
    public ArrayList<Edge> edges;  // 由该点出发的路径

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
