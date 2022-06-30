package BinaryTree;

public class MaxDistanceInTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxDistance(Node head) {
        return process(head).maxDistance;
    }

    // 信息返回的结构体
    public static class Info {
        public int maxDistance; 
        public int height;
        public Info(int dis, int h) {
            maxDistance = dis;
            height = h;
        }
    }

    // 返回以x为头的整棵树，两个信息
    public static Info process(Node x) {
        if(x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // info
        int p1 = leftInfo.maxDistance; // 可能性一：左树的最大距离
        int p2 = rightInfo.maxDistance; // 可能性二：右树的最大距离
        int p3 = leftInfo.height + 1 + rightInfo.height;  // 可能性三：贯穿左右的高度
        int maxDistance = Math.max(p3, Math.max(p1, p2));
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxDistance, height);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
    }


}
