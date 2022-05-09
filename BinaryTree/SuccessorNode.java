package BinaryTree;

public class SuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.right != null) {  // 情况一：Node有右子树
            return getLeftMost(node.right);  // get右树上最左节点
        } else { // 情况二：Node无右子树
            Node parent = node.parent;
            // 当前节点是其父节点的右子树
            while (parent != null && parent.left != node) { //parent.left != node node不是parent的左子树
                node = parent; // node向上回溯
                parent = node.parent; // parent向上回溯
            }
            // 两个停止条件：
            // 1. parent为空：类似于该节点是最末端的节点，没有后继节点，返回null
            // 2. parent.left == node，node是parent的左子树，返回parent
            return parent;
        }
    }

    // get右树上最左节点
    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
