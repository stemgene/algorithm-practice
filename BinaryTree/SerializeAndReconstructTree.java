package BinaryTree;

public class SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 以head为头的树，请序列化成字符串返回
    public static String serialByPre(Node head) {
        if (head == null) {
            return "#_";
        }
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // 反序列化第一步：创建队列
    public static Node reconByPreString(String preStr) {
        String[] values = preStr.split("_");
        // 把每一个值依次加入到队列中
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i != values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    // 反序列化第二步：消费queue，构建树
    public static Node reconPreOrder(Queue<String> queue) {
        // 把弹出的值赋给value
        String value = queue.poll();
        // 如果value是“#”，返回空节点
        if (value.equals("#")) {
            return null;
        }
        // 如果不是null，创建head节点
        Node head = new Node(Integer.valueOf(value));
        // 再建立左右子树
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }
}
