package LinkedList;
import java.util.*;

public class CopyListWithRandom {
    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    // 通过创建HashMap，空间复杂度O(N)
    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        // 克隆链表的值
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // cur 老
            // map.get(cur) 新
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        // 返回新结构的head，即老链表的head
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // copy node and link to every node
        // 1 -> 2
        // 1 -> 1' -> 2
        while(head != null) {
            next = cur.next;
            cur.next = new Node(cur.value);  // 当前节点的下一个放置其克隆节点
            cur.next.next = next;  // 克隆节点的再下一个，是原有节点的下一个
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        //set copy node rand
        // 1 -> 1' -> 2 -> 2'
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next; 
        }
        Node res = head.next;
        cur = head;
        // split
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;  // 返回新链表的头
    }
}
