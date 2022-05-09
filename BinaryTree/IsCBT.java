package BinaryTree;
import java.util.LinkedList;

public class IsCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        boolean leaf = false;  // 表示一个开关：是否遇到过左右两个孩子不双全的节点，遇到后改为true
        Node l = null;
        Node r = null;
        queue.add(head);  // 头节点先push到queue
        while (!queue.isEmpty()) {
            head = queue.poll();  // 弹出节点后
            l = head.left;  // 寻找左右子树
            r = head.right;
            // 每遇到一个节点时都判断
            if (
                (leaf && (l != null || r != null))  // leaf==true遇到两个孩子不双全，且当前节点不是叶节点
                ||
                (l == null && r != null)  // 左子树为空而右子树不为空
            ) {
                return false;
            }
            // 如果没有上述if判断，之研究以下queue的行为，就是宽度优先遍历
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
    }
}
