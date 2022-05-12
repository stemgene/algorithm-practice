package Graph;
import java.util.HashMap;

public class Graph {
    public HashMap<Integer, Node> nodes; // key是点的编号，value是实际的点
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
