package BinaryTree;

public class IsFull {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isF(Node head) {
        if (head == null) {
            return true;
        }
        Info data = process(head);
        return data.nodes == ((1 << data.height) - 1);
    }

    public static class Info {
        public int height;
        public int nodes;

        public Info(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static Info process(Node x) {
        if (x == null) {  // 当x为空时，返回（0，0）
            return new Info(0, 0);
        }
        Info leftData = process(x.left);
        Info rightData = process(x.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;

        return new Info(height, nodes);
    }
}
