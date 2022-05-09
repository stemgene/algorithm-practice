package BinaryTree;

public class IsBalancedTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isFull(Node head) {
        ReturnData allInfo = p(head);
        return ( 1 << allInfo.height - 1) == allInfo.nums;
    }

    public static class ReturnData {
        public int height;
        public int nums;

        public ReturnData(int h, int n) {
            height = h;
            nums = n;
        }
    }

    public static ReturnData p(Node x) {
        if (x == null) {
            return new ReturnData(0, 0);
        }
        ReturnData leftData = p(x.left);
        ReturnData rightData = p(x.right);

        int height = Math.max(leftData.height, rightData.height) + 1;

        int nums = leftData.nums + rightData.nums + 1;
        return new ReturnData(height, nums);
    }

    // 主函数，判断是否平衡，传入头节点
    public static boolean isBalanced(Node head) {
        return process(head).isBalanced;
    }

    // 构造函数：定义递归函数返回值的类型，返回两个值：是否平衡和高度
    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isB, int hei) {
            isBalanced = isB;
            height = hei;
        }
    }

    // 递归函数套路
    public static ReturnType process(Node x) {  // 以x为头
        if (x == null) {  // base case
            return new ReturnType(true, 0);  // 树为空时，返回true和高度0
        }
        //用黑盒
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        // 拆黑盒
        int height = Math.max(leftData.height, rightData.height) + 1;  // 左右树中较高的那个 + x本身的高度1
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                && Math.abs(leftData.height - rightData.height) < 2;
        // 返回，把整棵树连接起来
        return new ReturnType(isBalanced, height);
    }
}
