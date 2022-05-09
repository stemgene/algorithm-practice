package BinaryTree;

import java.util.HashMap;

public class LowestCommonAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方法一：17-49行
    // 前提是o1和o2一定属于head为头的树
    // 返回o1和o2的最低公共祖先
    public static Node lca(Node head, Node o1, Node o2) {
        // 存任何一个节点的父节点
        HashMap<Node, Node> fatherMap = new HashMap<Node, Node>();
        fatherMap.put(head, head);
        process(head, fatherMap); //完成此步骤，任何一个节点以及其父节点都会存到Map中
        // 通过HashSet记录o1往上整条链的节点
        HashSet<Node> set1 = new HashSet<>();
        Node cur = o1; 
        // 当cur不等于自己的父节点时（只有head才等于自己的父节点）
        // 将o1及其链上面的节点都存到set中
        while (cur != fatherMap.get(cur)) {
            set1.add(cur);
            cur = fatherMap.get(cur)
        }
        set1.add(head);  // 最后把head放入
        // 检查o2链上的节点是否在o1的set中,当出现的第一个重合点，就是二者的最低共同祖先
        // 后续流程需要自己补全
        //while

    }

    public static void process(Node head, HashMap<Node, Node> fatherMap) {
        if(head == null) {
            return;
        }
        // 存入每个节点和其父节点
        fatherMap.put(head.left, head);
        fatherMap.put(head.right, head);
        process(head.left, fatherMap);  // 每个节点都可以向上回溯
        process(head.right, fatherMap);
    }

    
    // 方法二：递归
    public static Node lowestAncestor(Node head, Node o1, Node o2) {
        // base case：head为空，或o1、o2有一个为head，那共同祖先一定是head
        if (head == null || head == o1 || head == o2) {
            return head;
        }

        Node left = lowestAncestor(head.left, o1, o2);  // 左树的返回值
        Node right = lowestAncestor(head.right, o1, o2);  // 右树的返回值
        // 左树的返回值和右树的返回值如果都不是空，返回head。此时说明o1和o2分别在左右子树
        if (left != null && right != null) {
            return head;
        }
        // 如果上述if不成立，说明两棵树里并不都有返回值，谁不为null返回谁
        return left != null ? left : right;
    }

    public static class Record1 {
        private HashMap<Node, Node> map;

        public Record1(Node head) {
            map = new HashMap<Node, Node>();
            if (head != null) {
                map.put(head, null);
            }
            setMap(head);
        }

        private void setMap(Node head) {
            if (head == null) {
                return;
            }
            if (head.left != null) {
                map.put(head.left, head);
            }
            if (head.right != null) {
                map.put(head.right, head);
            }
            setMap(head.left);
            setMap(head.right);
        }

        public Node query(Node o1, Node o2) {
            HashSet<Node> path = new HashSet<Node>();
            while (map.containsKey(o1)) {
                path.add(o1);
                o1 = map.get(o1);
            }
            while (!path.contains(o2)) {
                o2 = map.get(o2);
            }
            return o2;
        }
    }

    public static class Record2 {
        private HashMap<Node, HashMap<Node, Node>> map;

        public Record2(Node head) {
            map = new HashMap<Node, HashMap<Node, Node>>();
            initMap(head);
            setMap(head);
        }

        private void initMap(Node head) {
            if (head == null) {
                return;
            }
            map.put(head, new HashMap<Node, Node>());
            initMap(head.left);
            initMap(head.right);
        }

        private void setMap(Node head) {
            if (head == null) {
                return;
            }
            headRecord(head.left, head);
            headRecord(head.right, head);
            subRecord(head);
            setMap(head.left);
            setMap(head.right);
        }

        private void headRecord(Node n, Node h) {
            if (n == null) {
                return;
            }
            map.get(n).put(h, h);
            headRecord(n.left, h);
            headRecord(n.right, h);
        }

        private void subRecord(Node head) {
            if (head == null) {
                return;
            }
            preLeft(head.left, head.right, head);
            subRecord(head.left);
            subRecord(head.right);
        }

        private void preLeft(Node l, Node r, Node h) {
            if (l == null) {
                return;
            }
            preRight(l, r, h);
            preLeft(l.left, r, h);
            preLeft(l.right, r, h);
        }

        private void preRight(Node l, Node r, Node h) {
            if (r == null) {
                return;
            }
            map.get(l).put(r, h);
            preRight(l, r.left, h);
            preRight(l, r.right. h);
        }

        public Node query(Node o1, Node o2) {
            if (o1 == o2) {
                return o1;
            }
            if (map.containsKey(o1)) {
                return map.get(o1).get(o2);
            }
            return null;
        }

        // for test -- print tree
        public static void printTree(Node head) {
            System.out.println("Binary Tree");
            printInOrder(head, 0, "H", 17);
            System.out.println();
        }

        public static void printInOrder(Node head, int height, String to, int len) {
            if (head == null) {
                return;
            }
            printInOrder(head.right, height + 1, "v", len);
            String val = to + head.value + to;
            int lenM = val.length();
            int lenL = (len - lenM) / 2;
            int lenR = len - lenM - lenL;
            val = getSpace(lenL) + val + getSpace(lenR);
            System.out.println(getSpace(height * len) + val);
            printInOrder(head.left, height + 1, "^", len);
        }
    
        public static String getSpace(int num) {
            String space = " ";
            StringBuffer buf = new StringBuffer("");
            for (int i = 0; i < num; i++) {
                buf.append(space);
            }
            return buf.toString();
        }
    }
}
