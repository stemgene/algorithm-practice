package Graph;
import java.util.HashSet;
import java.util.Stack;

public class DFS {
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        // 深度优先 在点进入stack时处理
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();  // pop current node
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);  // 再次压入current node
                    stack.push(next);  // 压入node.next
                    set.add(next);
                    System.out.println(next.value);
                    break; // 只操作完一个next，不看其他的next nodes了
                }
            }
        }
    }

}
