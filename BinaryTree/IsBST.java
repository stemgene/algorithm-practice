package BinaryTree;

import java.util.Stack;

public class IsBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class ReturnData {
        public boolean isBST;
        public int min;
        public int max;

        public ReturnData(boolean is, int mi, int ma) {
            isBST = is;
            min = mi;
            max = ma;
        }
    }

    public static ReturnData process(Node x) {
        // base case
        if (x == null) {
            return null;
        }

        ReturnData leftData = process(x.left);
        ReturnData rightData = process(x.right);

        int min = x.value;
        int max = x.value;
        if(leftData != null) {  // 左侧有内容
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if(rightData != null) {  // 左侧有内容
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }
        
        boolean isBST = true;
        if (leftData != null && (!leftData.isBST || leftData.max >= x.value)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isBST || rightData.min <= x.value)) {
            isBST = false;
        }

        /* 48-54行的另一种写法
        boolean isBST = false;
        if (    // 左边如果有信息的情况下 ？ 要求左边是BST，并且max < x.value. 反之左侧如果为空，永远为true
                (leftData !=null ? (leftData.isBST && leftData.max < x.value) : true)
                &&
                (rightData != null) ? (rightData.isBST && rightData.min > x.value) : true)
        ) {
            isBST = true;
        }
        */

        return new ReturnData(isBST, min, max);
    }






    public static boolean inOrderUnRecur(Node head) {
        if (head == null) {
            return true;
        }
        int pre = Integer.MIN_VALUE;
        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.value <= pre) {
                    return false;
                }
                pre = head.value;
                head = head.right;
            }
        }
        return true;
    }

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> inOrderList = new LinkedList<>();
        process(head, inOrderList);
        int pre = Integer.MIN_VALUE;
        for (Node cur : inOrderList) {
            if (pre >= cur.value) {
                return false;
            }
            pre = cur.value;
        }
        return true;
    }

    public static void process(Node node, LinkedList<Node> inOrderList) {
        if (node == null) {
            return;
        }
        process(node.left, inOrderList);
        inOrderList.add(node);
        process(node.right, inOrderList);
    }
}
