package BinaryTree;
import java.util.HashMap;

public class TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 按照宽度遍历
    public static void w(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();  // 双链表，可以做队列
        queue.add(head);
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }
    
    
    
    // 寻找最多节点的一层的节点数
    public static void findNodesInMaxLevel(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();  // 双链表，可以做队列
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1;   // 当前在哪一层
        int curLevelNodes = 0;  //当前层发现了几个节点
        int max = Integer.MIN_VALUE;  // 哪一个层的节点最多
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            int curNodeLevel = levelMap.get(cur);   // 每弹出一个节点，先得到这个节点所在的层
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {  // 已经来到下一层了
                max = Math.max(max, curLevelNodes);  // 结算max
                curLevel++;
                curLevelNodes = 1;
            }
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
        }
        return max;
    }
}
