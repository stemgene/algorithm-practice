package BinaryTree;
import java.util.Stack;

public class PreInPosTraversal {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 前序
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");  // 只在第一次到达节点时打印
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    // 中序
    public static void InOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        InOrderRecur(head.left);
        System.out.print(head.value + " ");  // 只在第二次到达节点时打印
        InOrderRecur(head.right);
    }

    // 后序
    public static void postOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.print(head.value + " ");  // 只在第一次到达节点时打印
    }

    public static void preOrderUnRecur(Node head) {
        System.out.println("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);  // 先压head
            while (!stack.isEmpty()) {
                head = stack.pop();  // 从栈中弹出
                System.out.print(head.value + " ");  // 处理（打印）弹出的节点
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    public static void InOrderUnRecur(Node head) {
        System.out.println("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;  // head一直向左移动，把左边界压栈
                } else {
                    head = stack.pop();  // pop最上面的节点
                    System.out.print(head.value + " ");  // pop之后就打印
                    head = head.right;  // head移到右子树，继续上述if之后的过程
                }
            }
        }
        System.out.println();
    }

    public static void postOrderUnRecur1(Node head) {
        System.out.println("post-order: ");
        if (head != null) {
            // 栈和收集栈
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);  // push到收集栈中
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            // pop收集栈所有节点
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }
    
    public static void postOrderUnRecur2(Node head) {
        System.out.println("post-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && head != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = c;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        /**
         * 递归序：5，3，2，1，1，1，2，2，3，4，4，4，3，5，8，7，6，6，6，7，7，8，10，9，9，9，10，11，11，11，10，8，5
         * 前序：5，3，2，1，4，8，7，6，10，9，11
         * 中序：1，2，3，4，5，6，7，8，9，10，11
         * 后序：1，2，4，3，6，7，9，11，10，8，5
         */

        System.out.println("==================recursive================");
        System.out.println("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.println("in-order: ");
        InOrderRecur(head);
        System.out.println();
        System.out.println("post-order: ");
        postOrderRecur(head);
        System.out.println();

        System.out.println("==================unrecursive================");
        preOrderUnRecur(head);
        System.out.println();
        InOrderUnRecur(head);
        System.out.println();
        postOrderUnRecur1(head);
        System.out.println();
        postOrderUnRecur2(head);
    }
}
